package com.example.bostatask.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GameTrailersResponseDto(
    @SerializedName("results")
    val results: List<GameTrailerDto>? = null,
)

