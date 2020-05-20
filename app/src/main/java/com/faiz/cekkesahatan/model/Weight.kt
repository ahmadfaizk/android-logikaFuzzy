package com.faiz.cekkesahatan.model

data class Weight(
    val min: Int,
    val max: Int?,
    val type: WeightType,
    var index: Float = 1f
)