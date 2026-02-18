package com.example.bostatask.presentation.game_details_screen

import androidx.lifecycle.viewModelScope
import com.example.bostatask.domain.usecase.GetGameDetailsUseCase
import com.example.bostatask.domain.usecase.GetGameScreenshotsUseCase
import com.example.bostatask.domain.usecase.GetGameTrailersUseCase
import com.example.bostatask.presentation.base.BaseViewModel
import com.example.bostatask.presentation.base.ViewIntent
import kotlinx.coroutines.launch

class GameDetailsScreenViewModel(
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
    private val getGameTrailersUseCase: GetGameTrailersUseCase,
    private val getGameScreenshotsUseCase: GetGameScreenshotsUseCase,
) : BaseViewModel<GameDetailsScreenViewState, GameDetailsScreenIntent>() {

    override fun setInitViewState(): GameDetailsScreenViewState = GameDetailsScreenViewState()

    override fun reduce(intent: ViewIntent) {
        when (intent) {
            is GameDetailsScreenIntent.LoadDetails -> handleLoadDetails(intent.gameId)
            is GameDetailsScreenIntent.OnBackClicked -> handleBackClicked()
        }
    }

    private fun handleBackClicked() {
        setState { copy(navigation = GameDetailsScreenNavigation.Back) }
    }

    private fun handleLoadDetails(gameId: Int) {
        viewModelScope.launch {
            setState { copy(loading = true, error = null) }
            try {
                val details = getGameDetailsUseCase(gameId)
                val trailers = getGameTrailersUseCase(gameId)
                val screenshots = getGameScreenshotsUseCase(gameId)
                setState {
                    copy(
                        loading = false,
                        gameDetails = details,
                        trailers = trailers,
                        screenshots = screenshots,
                        error = null,
                    )
                }
            } catch (e: Exception) {
                setState {
                    copy(
                        loading = false,
                        gameDetails = null,
                        trailers = emptyList(),
                        screenshots = emptyList(),
                        error = "Failed to load game details! try again.",
                    )
                }
            }
        }
    }
}

