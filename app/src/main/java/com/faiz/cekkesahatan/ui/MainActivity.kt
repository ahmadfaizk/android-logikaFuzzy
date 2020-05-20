package com.faiz.cekkesahatan.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.faiz.cekkesahatan.R
import com.faiz.cekkesahatan.model.Health
import com.faiz.cekkesahatan.model.HealthType
import com.faiz.cekkesahatan.model.Height
import com.faiz.cekkesahatan.model.Result
import com.faiz.cekkesahatan.model.Weight
import com.faiz.cekkesahatan.utils.DataHelpers
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private var weight = 0f
    private var height = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_process.setOnClickListener {
            validateInput()
        }
    }

    private fun validateInput() {
        val weightString = edt_weight.text.toString()
        val heightString = edt_hight.text.toString()

        if (weightString.isEmpty()) {
            edt_weight.error = "Berat Harus Diisi"
            return
        }
        if (heightString.isEmpty()) {
            edt_hight.error = "Tinggi Harus Diisi"
            return
        }

        try {
            weight = weightString.toFloat()
            height = heightString.toFloat()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Format Berat/Tinggi Harus Angka", Toast.LENGTH_SHORT).show()
            return
        }

        calculate()
    }

    private fun calculate() {
        val listHeight = DataHelpers.getListHeight()
        val heightClass = ArrayList<Height>()
        for (height in listHeight) {
            if (height.min <= this.height) {
                if (height.max != null && height.max > this.height) {
                    heightClass.add(height)
                } else if (height.max == null) {
                    heightClass.add(height)
                }
            }
        }

        val listWeight = DataHelpers.getListWeight()
        val weightClass = ArrayList<Weight>()
        for (weight in listWeight) {
            if (weight.min <= this.weight) {
                if (weight.max != null && weight.max > this.weight) {
                    weightClass.add(weight)
                } else if (weight.max == null) {
                    weightClass.add(weight)
                }
            }
        }
        if (heightClass.size == 2) {
            heightClass[0].index = (heightClass[0].max!!-height)/(heightClass[0].max!!-heightClass[1].min)
            heightClass[1].index = (height-heightClass[1].min)/(heightClass[0].max!!-heightClass[1].min)
        }
        if (weightClass.size == 2) {
            weightClass[0].index = (weightClass[0].max!!-weight)/(weightClass[0].max!!-weightClass[1].min)
            weightClass[1].index = (weight-weightClass[1].min)/(weightClass[0].max!!-weightClass[1].min)
        }

        val listRules = DataHelpers.getRules()
        val listHealth = ArrayList<Health>()
        for (rules in listRules) {
            for (height in heightClass) {
                for (weight in weightClass) {
                    if (rules.height == height.type && rules.weight == weight.type) {
                        val health = getHealth(rules.health, height.index, weight.index)
                        health?.let { listHealth.add(it) }
                    }
                }
            }
        }
        var sumDecision = 0f
        var sumIndex = 0f
        for (health in listHealth) {
            sumIndex += health.index
            sumDecision += health.index*health.decisionIndex
        }
        val crispDecisionIndex = sumDecision/sumIndex
        val result = getResult(crispDecisionIndex).joinToString("\n")
        tv_output.text = result
    }

    private fun getHealth(healthType: HealthType, heightIndex: Float, weightIndex: Float): Health? {
        var result: Health? = null
        for (health in DataHelpers.getListHealth()) {
            if (health.type == healthType) {
                health.index = if (heightIndex <= weightIndex) heightIndex else weightIndex
                result = health
            }
        }
        return result
    }

    private fun getResult(crispDecisionIndex: Float): List<Result> {
        val listResult = ArrayList<Result>()
        val listHealth = DataHelpers.getListHealth()
        if (crispDecisionIndex <= listHealth[0].decisionIndex) {
            val fuzzyIndex = abs((crispDecisionIndex-0)/0.2*100).toFloat()
            listResult.add(Result(listHealth[0].type, fuzzyIndex))
        } else if (crispDecisionIndex <= listHealth[1].decisionIndex) {
            val fuzzyIndex2 = abs((crispDecisionIndex-0.2)/0.2*100).toFloat()
            val fuzzyIndex1 = abs((crispDecisionIndex-0.4)/0.2*100).toFloat()
            listResult.add(Result(listHealth[0].type, fuzzyIndex1))
            listResult.add(Result(listHealth[1].type, fuzzyIndex2))
        } else if (crispDecisionIndex <= listHealth[2].decisionIndex) {
            val fuzzyIndex2 = abs((crispDecisionIndex-0.4)/0.2*100).toFloat()
            val fuzzyIndex1 = abs((crispDecisionIndex-0.6)/0.2*100).toFloat()
            listResult.add(Result(listHealth[1].type, fuzzyIndex1))
            listResult.add(Result(listHealth[2].type, fuzzyIndex2))
        } else if (crispDecisionIndex <= listHealth[3].decisionIndex) {
            val fuzzyIndex2 = abs((crispDecisionIndex-0.6)/0.2*100).toFloat()
            val fuzzyIndex1 = abs((crispDecisionIndex-0.8)/0.2*100).toFloat()
            listResult.add(Result(listHealth[2].type, fuzzyIndex1))
            listResult.add(Result(listHealth[3].type, fuzzyIndex2))
        } else if (crispDecisionIndex > listHealth[3].decisionIndex) {
            val fuzzyIndex = abs((crispDecisionIndex-0.8)/0.2*100).toFloat()
            listResult.add(Result(listHealth[3].type, fuzzyIndex))
        }
        return listResult
    }
}
