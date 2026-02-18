package com.example.bostatask.presentation.games_list_screen

import com.example.bostatask.presentation.base.ViewNavigation

sealed class GamesScreenNavigation: ViewNavigation {
    data class ToGameDetails(val gameId: Int) : GamesScreenNavigation()
}