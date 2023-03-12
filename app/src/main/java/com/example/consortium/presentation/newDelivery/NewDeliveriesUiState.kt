package com.example.consortium.presentation.newDelivery

import com.example.consortium.domain.models.Trader
import com.example.consortium.presentation.delivery.DeliveriesUiState

sealed class NewDeliveriesUiState {
    class Success( val trader: Trader) : NewDeliveriesUiState()
    object Completed : NewDeliveriesUiState()
    object Empty : NewDeliveriesUiState()
}