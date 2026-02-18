package com.example.bostatask

import android.app.Application
import com.example.bostatask.di.networkModule
import com.example.bostatask.di.repositoryModule
import com.example.bostatask.di.useCaseModule
import com.example.bostatask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }
}