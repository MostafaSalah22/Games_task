package com.example.bostatask.di

import com.example.bostatask.domain.usecase.GetGameDetailsUseCase
import com.example.bostatask.domain.usecase.GetGameScreenshotsUseCase
import com.example.bostatask.domain.usecase.GetGameTrailersUseCase
import com.example.bostatask.domain.usecase.GetGamesUseCase
import com.example.bostatask.domain.usecase.GetGenresUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetGamesUseCase(get()) }
    factory { GetGameDetailsUseCase(get()) }
    factory { GetGameTrailersUseCase(get()) }
    factory { GetGameScreenshotsUseCase(get()) }
    factory { GetGenresUseCase(get()) }
}