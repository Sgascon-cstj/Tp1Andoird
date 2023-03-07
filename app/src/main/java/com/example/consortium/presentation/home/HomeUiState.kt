package com.example.consortium.presentation.home

import com.example.consortium.domain.models.Trader

sealed class HomeUiState {
    class Success(val trader: Trader) : HomeUiState()
    object Empty : HomeUiState()
}