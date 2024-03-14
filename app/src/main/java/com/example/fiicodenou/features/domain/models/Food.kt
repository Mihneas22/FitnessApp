package com.example.fiicodenou.features.domain.models

data class Food(
    val name: String = "",
    val calories: String = "",
    val weight: String = "",
    val protein: String = "",
    val carbohydrates: String = "",
    val fat: String = "",
    val approved: Boolean = false
)
