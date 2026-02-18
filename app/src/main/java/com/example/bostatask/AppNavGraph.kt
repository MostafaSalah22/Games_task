package com.example.bostatask

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bostatask.presentation.base.HandleNavigationEffect
import com.example.bostatask.presentation.game_details_screen.GameDetailsScreen
import com.example.bostatask.presentation.games_list_screen.GamesScreen
import com.example.bostatask.presentation.games_list_screen.GamesScreenNavigation
import com.example.bostatask.presentation.games_list_screen.GamesScreenViewModel
import org.koin.androidx.compose.koinViewModel

const val ROUTE_GAMES = "games"
const val ROUTE_GAME_DETAILS = "game_details/{gameId}"
const val ARG_GAME_ID = "gameId"

fun gameDetailsRoute(gameId: Int) = "game_details/$gameId"

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_GAMES,
        modifier = modifier,
    ) {
        composable(ROUTE_GAMES) {
            val viewModel: GamesScreenViewModel = koinViewModel()
            HandleNavigationEffect(viewModel) { nav ->
                when (nav) {
                    is GamesScreenNavigation.ToGameDetails -> {
                        navController.navigate(gameDetailsRoute(nav.gameId))
                    }
                    else -> {}
                }
            }
            GamesScreen(modifier = Modifier)
        }

        composable(
            route = ROUTE_GAME_DETAILS,
            arguments = listOf(
                navArgument(ARG_GAME_ID) { type = NavType.IntType },
            ),
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt(ARG_GAME_ID) ?: return@composable
            GameDetailsScreen(
                gameId = gameId,
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}
