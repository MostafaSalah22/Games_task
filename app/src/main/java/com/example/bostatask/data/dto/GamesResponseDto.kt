package com.example.bostatask.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GamesResponseDto(

    @SerializedName("results")
    val results: List<GameDto>? = null,

    @SerializedName("next")
    val next: String? = null

)
