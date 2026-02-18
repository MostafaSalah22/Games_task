package com.example.bostatask.domain.usecase

import com.example.bostatask.domain.models.GameScreenshot
import com.example.bostatask.domain.repository.GamesRepository

class GetGameScreenshotsUseCase(
    private val gamesRepository: GamesRepository,
) {
    suspend operator fun invoke(id: Int): List<GameScreenshot> {
        return gamesRepository.getGameScreenshots(id)
    }
}

