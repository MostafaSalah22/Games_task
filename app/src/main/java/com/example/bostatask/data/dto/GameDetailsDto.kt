package com.example.bostatask.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("background_image")
    val backgroundImage: String? = null,

    @SerializedName("released")
    val released: String? = null,

    @SerializedName("rating")
    val rating: Double? = null,

    @SerializedName("description_raw")
    val descriptionRaw: String? = null
)
