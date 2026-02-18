package com.example.bostatask.domain.usecase

import com.example.bostatask.domain.models.GameTrailer
import com.example.bostatask.domain.repository.GamesRepository

class GetGameTrailersUseCase(
    private val gamesRepository: GamesRepository,
) {
    suspend operator fun invoke(id: Int): List<GameTrailer> {
        return gamesRepository.getGameTrailers(id)
    }
}

