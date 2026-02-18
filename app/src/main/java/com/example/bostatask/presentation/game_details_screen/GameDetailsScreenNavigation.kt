package com.example.bostatask.presentation.game_details_screen

import com.example.bostatask.presentation.base.ViewNavigation

sealed class GameDetailsScreenNavigation : ViewNavigation {
    data object Back : GameDetailsScreenNavigation()
}

