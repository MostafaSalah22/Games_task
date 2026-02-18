package com.example.bostatask.di

import com.example.bostatask.data.repository.GamesRepositoryImpl
import com.example.bostatask.domain.repository.GamesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<GamesRepository> {
        GamesRepositoryImpl(get())
    }
}