package com.example.bostatask.presentation.game_details_screen

import com.example.bostatask.domain.models.GameDetails
import com.example.bostatask.domain.models.GameTrailer
import com.example.bostatask.domain.models.GameScreenshot
import com.example.bostatask.presentation.base.ViewNavigation
import com.example.bostatask.presentation.base.ViewState

data class GameDetailsScreenViewState(
    override val loading: Boolean = false,
    override var navigation: ViewNavigation? = null,
    val gameDetails: GameDetails? = null,
    val trailers: List<GameTrailer> = emptyList(),
    val screenshots: List<GameScreenshot> = emptyList(),
    val error: String? = null,
): ViewState()

