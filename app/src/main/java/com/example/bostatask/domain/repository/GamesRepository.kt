package com.example.bostatask.domain.repository

import androidx.paging.PagingData
import com.example.bostatask.domain.models.Game
import com.example.bostatask.domain.models.GameDetails
import com.example.bostatask.domain.models.GameTrailer
import com.example.bostatask.domain.models.GameScreenshot
import com.example.bostatask.domain.models.Genre
import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    suspend fun getGames(genreId: Int): Flow<PagingData<Game>>

    suspend fun getGameDetails(
        id: Int
    ): GameDetails

    suspend fun getGenres(): List<Genre>

    suspend fun getGameTrailers(
        id: Int
    ): List<GameTrailer>

    suspend fun getGameScreenshots(
        id: Int
    ): List<GameScreenshot>
}