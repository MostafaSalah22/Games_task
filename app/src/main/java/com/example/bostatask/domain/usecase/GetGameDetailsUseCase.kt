package com.example.bostatask.domain.usecase

import com.example.bostatask.domain.models.GameDetails
import com.example.bostatask.domain.repository.GamesRepository

class GetGameDetailsUseCase(
    private val gamesRepository: GamesRepository
) {
    suspend operator fun invoke(id: Int): GameDetails {
        return gamesRepository.getGameDetails(id)
    }
}