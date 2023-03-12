package com.example.consortium.presentation.delivery

sealed class DeliveriesUiState {
    class Success( val name: String) : DeliveriesUiState()

    object Empty : DeliveriesUiState()
}