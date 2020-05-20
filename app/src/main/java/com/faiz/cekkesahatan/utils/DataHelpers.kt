package com.faiz.cekkesahatan.utils

import com.faiz.cekkesahatan.model.*

object DataHelpers {

    fun getListHeight(): List<Height> {
        val listHeight = ArrayList<Height>()
        listHeight.add(Height(0, 120, HeightType.SP))
        listHeight.add(Height(115, 145, HeightType.P))
        listHeight.add(Height(140, 165, HeightType.S))
        listHeight.add(Height(160, 185, HeightType.T))
        listHeight.add(Height(180, null, HeightType.ST))
        return listHeight
    }

    fun getListWeight(): List<Weight> {
        val listWeight = ArrayList<Weight>()
        listWeight.add(Weight(0, 45, WeightType.SK))
        listWeight.add(Weight(40, 55, WeightType.K))
        listWeight.add(Weight(50, 65, WeightType.S))
        listWeight.add(Weight(60, 85, WeightType.B))
        listWeight.add(Weight(80, null, WeightType.SB))
        return listWeight
    }

    fun getListHealth(): List<Health> {
        val listHealth = ArrayList<Health>()
        listHealth.add(Health(0.2f, HealthType.TS))
        listHealth.add(Health(0.4f, HealthType.AS))
        listHealth.add(Health(0.6f, HealthType.S))
        listHealth.add(Health(0.8f, HealthType.SS))
        return listHealth
    }

    fun getRules(): List<Rules> {
        val rules = ArrayList<Rules>()
        rules.add(Rules(WeightType.SK, HeightType.SP, HealthType.SS))
        rules.add(Rules(WeightType.SK, HeightType.P, HealthType.S))
        rules.add(Rules(WeightType.SK, HeightType.S, HealthType.AS))
        rules.add(Rules(WeightType.SK, HeightType.T, HealthType.TS))
        rules.add(Rules(WeightType.SK, HeightType.ST, HealthType.TS))
        rules.add(Rules(WeightType.K, HeightType.SP, HealthType.S))
        rules.add(Rules(WeightType.K, HeightType.P, HealthType.SS))
        rules.add(Rules(WeightType.K, HeightType.S, HealthType.SS))
        rules.add(Rules(WeightType.K, HeightType.T, HealthType.S))
        rules.add(Rules(WeightType.K, HeightType.ST, HealthType.AS))
        rules.add(Rules(WeightType.S, HeightType.SP, HealthType.AS))
        rules.add(Rules(WeightType.S, HeightType.P, HealthType.S))
        rules.add(Rules(WeightType.S, HeightType.S, HealthType.SS))
        rules.add(Rules(WeightType.S, HeightType.T, HealthType.SS))
        rules.add(Rules(WeightType.S, HeightType.ST, HealthType.SS))
        rules.add(Rules(WeightType.B, HeightType.SP, HealthType.TS))
        rules.add(Rules(WeightType.B, HeightType.P, HealthType.AS))
        rules.add(Rules(WeightType.B, HeightType.S, HealthType.AS))
        rules.add(Rules(WeightType.B, HeightType.T, HealthType.S))
        rules.add(Rules(WeightType.B, HeightType.ST, HealthType.S))
        rules.add(Rules(WeightType.SB, HeightType.SP, HealthType.TS))
        rules.add(Rules(WeightType.SB, HeightType.P, HealthType.TS))
        rules.add(Rules(WeightType.SB, HeightType.S, HealthType.TS))
        rules.add(Rules(WeightType.SB, HeightType.T, HealthType.TS))
        rules.add(Rules(WeightType.SB, HeightType.ST, HealthType.AS))
        return rules
    }
}