package com.example.bostatask.data.mapper

import com.example.bostatask.data.dto.GameTrailerDto
import com.example.bostatask.domain.models.GameTrailer

fun GameTrailerDto.toDomain(): GameTrailer {
    return GameTrailer(
        id = id ?: -1,
        name = name.orEmpty(),
        previewImageUrl = preview.orEmpty(),
        videoUrl = data?.max.orEmpty(),
    )
}

