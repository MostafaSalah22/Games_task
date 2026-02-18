package com.example.bostatask.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.bostatask.data.mapper.toDomain
import com.example.bostatask.data.paging.GamesPagingSource
import com.example.bostatask.data.remote.RawgApi
import com.example.bostatask.domain.models.Game
import com.example.bostatask.domain.models.GameDetails
import com.example.bostatask.domain.models.GameTrailer
import com.example.bostatask.domain.models.GameScreenshot
import com.example.bostatask.domain.models.Genre
import com.example.bostatask.domain.repository.GamesRepository
import kotlinx.coroutines.flow.Flow

class GamesRepositoryImpl(
    private val api: RawgApi
) : GamesRepository {

    override suspend fun getGames(genreId: Int): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GamesPagingSource(api, genreId)
            }
        ).flow
    }

    override suspend fun getGameDetails(id: Int): GameDetails {
        return api.getGameDetails(id).toDomain()
    }

    override suspend fun getGenres(): List<Genre> {
        return try {
            api.getGenres()
                .results
                .orEmpty()
                .map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getGameTrailers(id: Int): List<GameTrailer> {
        return try {
            api.getGameTrailers(id)
                .results
                .orEmpty()
                .map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getGameScreenshots(id: Int): List<GameScreenshot> {
        return try {
            api.getGameScreenshots(id)
                .results
                .orEmpty()
                .map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}

