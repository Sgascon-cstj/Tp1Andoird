package com.example.consortium.presentation.delivery

import com.example.consortium.domain.models.Delivery

sealed class DeliveriesUiState {
    class Success( val name: String, val deliveries: List<Delivery>) : DeliveriesUiState()

    object Empty : DeliveriesUiState()
}