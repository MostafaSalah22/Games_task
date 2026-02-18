package com.example.bostatask.presentation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun HandleNavigationEffect(
    viewModel: BaseViewModel<out ViewState, out ViewIntent>,
    onNavigationTriggered: (navigationEffect: ViewNavigation?) -> Unit
) {
    LaunchedEffect(key1 = null) {
        viewModel.viewState.collect {
            onNavigationTriggered(it.navigation)
            viewModel.navigationConsumed()
        }
    }
}