package com.example.consortium.presentation.delivery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.consortium.domain.repositories.TraderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeliveriesViewModel(application: Application) : AndroidViewModel(application) {
    private val traderRepository = TraderRepository(application)
    private val _deliveriesUiState = MutableStateFlow<DeliveriesUiState>(DeliveriesUiState.Empty)
    val deliveriesUiState = _deliveriesUiState.asStateFlow()
    var traderName = ""
    init {
        viewModelScope.launch {
            traderRepository.trader.collect{ trader->
                traderName = trader.name

                _deliveriesUiState.update {
                    DeliveriesUiState.Success(traderName)
                }
            }
        }
    }
}