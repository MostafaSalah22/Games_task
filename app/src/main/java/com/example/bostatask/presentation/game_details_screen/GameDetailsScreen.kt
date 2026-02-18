package com.example.bostatask.presentation.game_details_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.bostatask.presentation.base.HandleNavigationEffect
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.unit.dp
import com.example.bostatask.presentation.game_details_screen.composable.GameDetailsTopBar
import com.example.bostatask.presentation.game_details_screen.composable.GameDetailsLoadingContent
import com.example.bostatask.presentation.game_details_screen.composable.GameDetailsErrorContent
import com.example.bostatask.presentation.game_details_screen.composable.GameDetailsInfoContent

@Composable
fun GameDetailsScreen(
    gameId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GameDetailsScreenViewModel = koinViewModel(),
) {
    val state by viewModel.viewState.collectAsState()

    LaunchedEffect(gameId) {
        viewModel.sendIntent(GameDetailsScreenIntent.LoadDetails(gameId))
    }

    HandleNavigationEffect(viewModel) { nav ->
        when (nav) {
            is GameDetailsScreenNavigation.Back -> onBackClick()
            else -> {}
        }
    }

    GameDetailsScreenContent(
        modifier = modifier,
        state = state,
        gameId = gameId,
        executeIntent = { viewModel.sendIntent(it) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsScreenContent(
    modifier: Modifier,
    state: GameDetailsScreenViewState,
    gameId: Int,
    executeIntent: (GameDetailsScreenIntent) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            GameDetailsTopBar(
                onBackClick = { executeIntent(GameDetailsScreenIntent.OnBackClicked) },
            )
        },
    ) { innerPadding ->
        when {
            state.loading -> {
                GameDetailsLoadingContent(
                    modifier = Modifier.padding(innerPadding),
                )
            }
            state.error != null -> {
                GameDetailsErrorContent(
                    errorMessage = state.error,
                    onRetryClick = { executeIntent(GameDetailsScreenIntent.LoadDetails(gameId)) },
                    modifier = Modifier.padding(innerPadding),
                )
            }
            state.gameDetails != null -> {
                GameDetailsInfoContent(
                    details = state.gameDetails,
                    trailers = state.trailers,
                    screenshots = state.screenshots,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}
