package com.example.bostatask.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GameTrailerDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("preview")
    val preview: String? = null,

    @SerializedName("data")
    val data: TrailerDataDto? = null,
)

@Serializable
data class TrailerDataDto(
    @SerializedName("max")
    val max: String? = null,
)

