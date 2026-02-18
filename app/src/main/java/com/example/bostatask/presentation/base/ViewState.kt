package com.example.bostatask.presentation.base

import androidx.compose.runtime.Stable

@Stable
abstract class ViewState {
    abstract val loading: Boolean
    abstract var navigation: ViewNavigation?
}



