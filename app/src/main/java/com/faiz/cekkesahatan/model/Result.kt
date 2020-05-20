package com.faiz.cekkesahatan.model

data class Result(
    val healthType: HealthType,
    val fuzzyIndex: Float
) {
    override fun toString(): String {
        val index = "%.2f".format(fuzzyIndex)
        return "$healthType: $index%"
    }
}