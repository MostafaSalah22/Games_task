package com.example.bostatask.data.remote

import com.example.bostatask.data.dto.GameDetailsDto
import com.example.bostatask.data.dto.GameTrailersResponseDto
import com.example.bostatask.data.dto.GameScreenshotsResponseDto
import com.example.bostatask.data.dto.GamesResponseDto
import com.example.bostatask.data.dto.GenresResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgApi {
    @GET("games")
    suspend fun getGames(
        @Query("genres") genreId: Int,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): GamesResponseDto

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Int
    ): GameDetailsDto

    @GET("games/{id}/movies")
    suspend fun getGameTrailers(
        @Path("id") id: Int
    ): GameTrailersResponseDto

    @GET("games/{id}/screenshots")
    suspend fun getGameScreenshots(
        @Path("id") id: Int
    ): GameScreenshotsResponseDto

    @GET("genres")
    suspend fun getGenres(): GenresResponseDto
}

