package com.example.bostatask.presentation.games_list_screen

import com.example.bostatask.presentation.base.ViewIntent

sealed class GamesScreenIntent: ViewIntent {
    data class LoadGames(val genreId: Int) : GamesScreenIntent()
    data object LoadGenres : GamesScreenIntent()
    data object Retry : GamesScreenIntent()
    data class SelectGenre(val genreId: Int) : GamesScreenIntent()
    data class UpdateSearchQuery(val query: String) : GamesScreenIntent()
    data class OpenGameDetails(val gameId: Int) : GamesScreenIntent()
}