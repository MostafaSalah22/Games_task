package com.example.bostatask.domain.usecase

import androidx.paging.PagingData
import com.example.bostatask.domain.models.Game
import com.example.bostatask.domain.repository.GamesRepository
import kotlinx.coroutines.flow.Flow

class GetGamesUseCase(
    private val gamesRepository: GamesRepository
) {
    suspend operator fun invoke(
        genreId: Int,
    ): Flow<PagingData<Game>> {
        return gamesRepository.getGames(
            genreId = genreId
        )
    }
}