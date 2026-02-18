package com.example.bostatask.presentation.games_list_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bostatask.presentation.games_list_screen.composable.GamesSearchBar
import com.example.bostatask.presentation.games_list_screen.composable.GenreDropdown
import com.example.bostatask.presentation.games_list_screen.composable.GamesListContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun GamesScreen(
    viewModel: GamesScreenViewModel = koinViewModel(),
    modifier: Modifier
) {
    val state by viewModel.viewState.collectAsState()

    GamesScreenContent(
        modifier = modifier,
        state = state,
        executeIntent = { viewModel.sendIntent(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreenContent(
    modifier: Modifier,
    state: GamesScreenViewState,
    executeIntent: (GamesScreenIntent) -> Unit,
) {
    LaunchedEffect(state.isInitialized) {
        if (!state.isInitialized) {
            executeIntent(GamesScreenIntent.LoadGenres)
            executeIntent(
                GamesScreenIntent.LoadGames(
                    state.selectedGenreId ?: state.genres.firstOrNull()?.id ?: 1,
                ),
            )
        }
    }

    val games = state.gamesPagingFlow.collectAsLazyPagingItems()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        GenreDropdown(
            genres = state.genres,
            selectedGenreId = state.selectedGenreId,
            onGenreSelected = { id ->
                executeIntent(GamesScreenIntent.SelectGenre(id))
            }
        )

        GamesSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { executeIntent(GamesScreenIntent.UpdateSearchQuery(it)) },
        )

        GamesListContent(
            modifier = Modifier.fillMaxSize(),
            games = games,
            searchQuery = state.searchQuery,
            onGameClick = { id -> executeIntent(GamesScreenIntent.OpenGameDetails(id)) },
            onRetryInitial = { executeIntent(GamesScreenIntent.Retry) },
            onRetryAppend = { games.retry() },
        )
    }
}