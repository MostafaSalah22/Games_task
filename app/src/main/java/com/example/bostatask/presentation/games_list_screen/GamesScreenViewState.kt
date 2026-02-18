package com.example.bostatask.presentation.games_list_screen

import androidx.paging.PagingData
import com.example.bostatask.domain.models.Game
import com.example.bostatask.domain.models.Genre
import com.example.bostatask.presentation.base.ViewNavigation
import com.example.bostatask.presentation.base.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
data class GamesScreenViewState(
    override val loading: Boolean = false,
    override var navigation: ViewNavigation? = null,
    val gamesPagingFlow: Flow<PagingData<Game>> = emptyFlow(),
    val genres: List<Genre> = emptyList(),
    val selectedGenreId: Int? = null,
    val searchQuery: String = "",
    val isInitialized: Boolean = false,
): ViewState()
