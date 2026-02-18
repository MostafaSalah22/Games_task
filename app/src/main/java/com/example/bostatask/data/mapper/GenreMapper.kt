package com.example.bostatask.data.mapper

import com.example.bostatask.data.dto.GenreDto
import com.example.bostatask.domain.models.Genre

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = id ?: -1,
        name = name.orEmpty(),
    )
}

