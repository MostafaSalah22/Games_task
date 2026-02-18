package com.example.bostatask.di

import com.example.bostatask.presentation.game_details_screen.GameDetailsScreenViewModel
import com.example.bostatask.presentation.games_list_screen.GamesScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::GamesScreenViewModel)
    viewModelOf(::GameDetailsScreenViewModel)
}