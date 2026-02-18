package com.example.bostatask.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GameScreenshotDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("image")
    val image: String? = null,
)

