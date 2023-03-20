package com.example.consortium.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consortium.core.AppDatabase
import com.example.consortium.domain.models.Trader
import com.example.consortium.domain.repositories.TraderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel (application: Application) : AndroidViewModel(application) {
    private val traderRepository = TraderRepository(application)
    private val deliveryRepository = AppDatabase.getInstance(application).deliveryRepository()
    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Empty)
    val homeUiState = _homeUiState.asStateFlow()
    private var currentTrader = Trader("",0.0f,0.0f,0.0f,0.0f,0.0f)
    init {
        viewModelScope.launch {
            traderRepository.trader.collect{ trader ->
                currentTrader = trader
                _homeUiState.update {
                    HomeUiState.Success(trader)
                }

            }
        }
    }
    fun save( ) {
        viewModelScope.launch {
            traderRepository.save(currentTrader)
        }
    }
    fun recharge(){

        viewModelScope.launch {
            traderRepository.recharge(currentTrader)
            traderRepository.trader.collect{ trader ->
                currentTrader = trader
                _homeUiState.update {
                    HomeUiState.Success(trader)
                }

            }
        }
    }
    fun upload(){
        viewModelScope.launch {
            deliveryRepository.deleteAll()
        }
        viewModelScope.launch {
            traderRepository.upload(currentTrader)
            traderRepository.trader.collect{ trader ->
                currentTrader = trader
                _homeUiState.update {
                    HomeUiState.Success(trader)
                }

            }
        }
    }

}