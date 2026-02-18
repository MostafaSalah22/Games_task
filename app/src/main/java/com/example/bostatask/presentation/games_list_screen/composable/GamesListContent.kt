package com.example.bostatask.presentation.games_list_screen.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.bostatask.domain.models.Game
import com.example.bostatask.presentation.games_list_screen.GamesScreenIntent

@Composable
fun GamesListContent(
    modifier: Modifier = Modifier,
    games: LazyPagingItems<Game>,
    searchQuery: String,
    onGameClick: (Int) -> Unit,
    onRetryInitial: () -> Unit,
    onRetryAppend: () -> Unit,
) {
    val query = searchQuery.trim()
    var frozenItemCount by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(query) {
        if (query.isNotBlank()) {
            if (frozenItemCount == null) frozenItemCount = games.itemCount
        } else {
            frozenItemCount = null
        }
    }

    val itemCountToShow =
        if (query.isBlank()) games.itemCount else (frozenItemCount ?: games.itemCount).coerceAtMost(games.itemCount)

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 8.dp,
            bottom = 80.dp,
        ),
    ) {
        items(itemCountToShow) { index ->
            val game = games[index]
            val matchesSearch = query.isBlank() ||
                game?.name?.contains(query, ignoreCase = true) == true
            if (game != null && matchesSearch) {
                GameCard(
                    game = game,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    onClick = { onGameClick(game.id) },
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.dp),
                )
            }
        }

        games.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        CustomIndicator()
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = "Error loading games")
                            Button(onClick = onRetryInitial) {
                                Text("Retry")
                            }
                        }
                    }
                }

                query.isBlank() && loadState.append is LoadState.Loading -> {
                    item {
                        CustomIndicator()
                    }
                }

                query.isBlank() && loadState.append is LoadState.Error -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = "Error loading more games")
                            Button(onClick = onRetryAppend) {
                                Text("Retry")
                            }
                        }
                    }
                }
            }
        }
    }
}

