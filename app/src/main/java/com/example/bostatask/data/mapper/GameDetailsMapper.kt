package com.example.bostatask.data.mapper

import com.example.bostatask.data.dto.GameDetailsDto
import com.example.bostatask.domain.models.GameDetails

fun GameDetailsDto.toDomain(): GameDetails {
    return GameDetails(
        id = id ?: -1,
        name = name.orEmpty(),
        imageUrl = backgroundImage.orEmpty(),
        released = released.orEmpty(),
        rating = rating ?: 0.0,
        description = descriptionRaw.orEmpty()
    )
}