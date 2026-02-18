package com.example.bostatask.presentation.games_list_screen

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.bostatask.domain.usecase.GetGamesUseCase
import com.example.bostatask.domain.usecase.GetGenresUseCase
import com.example.bostatask.presentation.base.BaseViewModel
import com.example.bostatask.presentation.base.ViewIntent
import kotlinx.coroutines.launch
class GamesScreenViewModel(
    private val getGamesUseCase: GetGamesUseCase,
    private val getGenresUseCase: GetGenresUseCase,
): BaseViewModel<GamesScreenViewState, GamesScreenIntent>() {
    override fun setInitViewState(): GamesScreenViewState = GamesScreenViewState()

    override fun reduce(intent: ViewIntent) {
        when(intent) {
            is GamesScreenIntent.LoadGames -> handleLoadGames(intent.genreId)
            is GamesScreenIntent.LoadGenres -> handleLoadGenres()
            is GamesScreenIntent.Retry -> handleRetry()
            is GamesScreenIntent.SelectGenre -> handleSelectGenre(intent.genreId)
            is GamesScreenIntent.UpdateSearchQuery -> handleUpdateSearchQuery(intent.query)
            is GamesScreenIntent.OpenGameDetails -> handleOpenGameDetails(intent.gameId)
        }
    }

    private fun handleRetry() {
        handleLoadGenres()
        handleLoadGames(viewState.value.selectedGenreId ?: 1)
    }

    private fun handleOpenGameDetails(gameId: Int) {
        setState { copy(navigation = GamesScreenNavigation.ToGameDetails(gameId)) }
    }

    private fun handleUpdateSearchQuery(query: String) {
        setState { copy(searchQuery = query) }
    }

    private fun handleLoadGames(genreId: Int) {
        viewModelScope.launch {

            val flow = getGamesUseCase(genreId).cachedIn(viewModelScope)

            setState {
                copy(
                    gamesPagingFlow = flow,
                    selectedGenreId = genreId
                )
            }
        }
    }

    private fun handleLoadGenres() {
        viewModelScope.launch {
            try {
                val genres = getGenresUseCase()
                setState {
                    copy(
                        genres = genres,
                        isInitialized = true,
                    )
                }
            } catch (e: Exception) {
                setState { copy(genres = emptyList()) }
            }
        }
    }

    private fun handleSelectGenre(genreId: Int) {
        handleLoadGames(genreId)
    }
}