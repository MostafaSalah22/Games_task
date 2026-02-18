package com.example.bostatask.domain.usecase

import com.example.bostatask.domain.models.Genre
import com.example.bostatask.domain.repository.GamesRepository

class GetGenresUseCase(
    private val gamesRepository: GamesRepository
) {
    suspend operator fun invoke(): List<Genre> {
        return gamesRepository.getGenres()
    }
}

