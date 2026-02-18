package com.example.bostatask.data.mapper

import com.example.bostatask.data.dto.GameDto
import com.example.bostatask.domain.models.Game

fun GameDto.toDomain(): Game {
    return Game(
        id = id ?: -1,
        name = name.orEmpty(),
        imageUrl = backgroundImage.orEmpty(),
        rating = rating ?: 0.0
    )
}