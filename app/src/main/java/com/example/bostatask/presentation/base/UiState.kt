package com.example.bostatask.presentation.base

sealed class UiState {
    //object Idle : UiState<Nothing>()
    object Loading : UiState()
    object Success : UiState()
    object Error : UiState()
    object Empty : UiState()
}