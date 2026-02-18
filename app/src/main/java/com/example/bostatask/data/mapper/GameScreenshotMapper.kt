package com.example.bostatask.data.mapper

import com.example.bostatask.data.dto.GameScreenshotDto
import com.example.bostatask.domain.models.GameScreenshot

fun GameScreenshotDto.toDomain(): GameScreenshot {
    return GameScreenshot(
        id = id ?: -1,
        imageUrl = image.orEmpty(),
    )
}

