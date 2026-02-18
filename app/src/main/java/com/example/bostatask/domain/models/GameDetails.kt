package com.example.bostatask.domain.models

data class GameDetails(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val released: String,
    val rating: Double,
    val description: String
)
