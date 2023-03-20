package com.example.consortium.presentation.newDelivery

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.consortium.core.AppDatabase
import com.example.consortium.domain.models.Delivery
import com.example.consortium.domain.models.Trader
import com.example.consortium.domain.repositories.DeliveryRepository
import com.example.consortium.domain.repositories.TraderRepository
import com.example.consortium.presentation.delivery.DeliveriesUiState
import com.example.consortium.presentation.home.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewDeliveriesViewModel(application: Application) : AndroidViewModel(application) {
    private val traderRepository = TraderRepository(application)
    private  val deliveryRepository = AppDatabase.getInstance(application).deliveryRepository()
    private val _newDeliveriesUiState = MutableStateFlow<NewDeliveriesUiState>(NewDeliveriesUiState.Empty)

    val newDeliveriesUiState = _newDeliveriesUiState.asStateFlow()
    private var currentTrader = Trader("",0.0f,0.0f,0.0f,0.0f,0.0f)
    init {
        viewModelScope.launch {
            traderRepository.trader.collect{ trader ->
                currentTrader = trader
                _newDeliveriesUiState.update {
                    NewDeliveriesUiState.Success(trader)
                }

            }
        }
    }
    fun save(fr: Float, k:Float,ve:Float, ye:Float, z:Float){
        if (fr <= 0 && k <= 0 && ve <= 0 && ye <= 0 && z <= 0){
            viewModelScope.launch {
                _newDeliveriesUiState.update {
                    NewDeliveriesUiState.Error
                }
            }
        }else {
            val delivery = Delivery(fr, k, ve, ye, z)
            viewModelScope.launch {

                deliveryRepository.insert(delivery)
                _newDeliveriesUiState.update {
                    NewDeliveriesUiState.Completed
                }
            }
            viewModelScope.launch {
                currentTrader.froynyx -= fr
                currentTrader.kreotrium -= k
                currentTrader.vethynx -= ve
                currentTrader.yerfrium -= ye
                currentTrader.zuscum -= z
                traderRepository.save(currentTrader)
                _newDeliveriesUiState.update {
                    NewDeliveriesUiState.Completed
                }
            }
        }
    }
}