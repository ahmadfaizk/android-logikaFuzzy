package com.faiz.cekkesahatan.model

data class Height(
    val min: Int,
    val max: Int?,
    val type: HeightType,
    var index: Float = 1f
)