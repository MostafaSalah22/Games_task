package com.example.bostatask.presentation.game_details_screen

import com.example.bostatask.presentation.base.ViewIntent

sealed class GameDetailsScreenIntent : ViewIntent {
    data class LoadDetails(val gameId: Int) : GameDetailsScreenIntent()
    data object OnBackClicked : GameDetailsScreenIntent()
}
