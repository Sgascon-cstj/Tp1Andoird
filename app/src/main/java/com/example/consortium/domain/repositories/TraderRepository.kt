package com.example.consortium.domain.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.consortium.core.Constants.dataStore
import com.example.consortium.domain.models.Delivery
import com.example.consortium.domain.models.Trader
import kotlinx.coroutines.flow.map
import kotlin.random.Random

class TraderRepository(private val context: Context){


    object PreferencesKeys {
        val USERNAME = stringPreferencesKey("username")
        val QUANTITY_ELEMENT_FR = floatPreferencesKey("element_FR")
        val QUANTITY_ELEMENT_K = floatPreferencesKey("element_K")
        val QUANTITY_ELEMENT_VE = floatPreferencesKey("element_VE")
        val QUANTITY_ELEMENT_YE = floatPreferencesKey("element_YE")
        val QUANTITY_ELEMENT_Z = floatPreferencesKey("element_Z")
    }

    val trader = context.dataStore.data.map {
        val name = it[PreferencesKeys.USERNAME] ?: ""
        val fr = it[PreferencesKeys.QUANTITY_ELEMENT_FR] ?: 200.0f
        val k = it[PreferencesKeys.QUANTITY_ELEMENT_K] ?: 200.0f
        val ve = it[PreferencesKeys.QUANTITY_ELEMENT_VE] ?: 200.0f
        val ye = it[PreferencesKeys.QUANTITY_ELEMENT_YE] ?: 200.0f
        val z = it[PreferencesKeys.QUANTITY_ELEMENT_Z] ?: 200.0f
        return@map Trader(name, fr,k,ve,ye,z)
    }
    suspend fun save(trader: Trader) {
        context.dataStore.edit {
            it[PreferencesKeys.USERNAME] = trader.name
            it[PreferencesKeys.QUANTITY_ELEMENT_FR] = trader.froynyx
            it[PreferencesKeys.QUANTITY_ELEMENT_K] = trader.kreotrium
            it[PreferencesKeys.QUANTITY_ELEMENT_VE] = trader.vethynx
            it[PreferencesKeys.QUANTITY_ELEMENT_YE] = trader.yerfrium
            it[PreferencesKeys.QUANTITY_ELEMENT_Z] = trader.zuscum
        }
    }
    suspend fun recharge(trader: Trader) {
        context.dataStore.edit {
            it[PreferencesKeys.QUANTITY_ELEMENT_FR] = (trader.froynyx + Random.nextInt(50,200))
            it[PreferencesKeys.QUANTITY_ELEMENT_K] = trader.kreotrium+ Random.nextInt(50,200)
            it[PreferencesKeys.QUANTITY_ELEMENT_VE] = trader.vethynx+ Random.nextInt(50,200)
            it[PreferencesKeys.QUANTITY_ELEMENT_YE] = trader.yerfrium+ Random.nextInt(50,200)
            it[PreferencesKeys.QUANTITY_ELEMENT_Z] = trader.zuscum+ Random.nextInt(50,200)
        }
    }
    suspend fun upload(trader: Trader) {
        context.dataStore.edit {
            it[PreferencesKeys.QUANTITY_ELEMENT_FR] = 200.0f
            it[PreferencesKeys.QUANTITY_ELEMENT_K] = 200.0f
            it[PreferencesKeys.QUANTITY_ELEMENT_VE] = 200.0f
            it[PreferencesKeys.QUANTITY_ELEMENT_YE] = 200.0f
            it[PreferencesKeys.QUANTITY_ELEMENT_Z] = 200.0f
        }
    }
    suspend fun saveNewDelivery(delivery: Delivery){
        context.dataStore.edit {
            it[PreferencesKeys.QUANTITY_ELEMENT_FR]?.minus(delivery.froynyx.toFloat())
            it[PreferencesKeys.QUANTITY_ELEMENT_K]?.minus(delivery.kreotrium.toFloat())
            it[PreferencesKeys.QUANTITY_ELEMENT_VE]?.minus(delivery.vethynx.toFloat())
            it[PreferencesKeys.QUANTITY_ELEMENT_YE]?.minus(delivery.yerfrium.toFloat())
            it[PreferencesKeys.QUANTITY_ELEMENT_Z]?.minus(delivery.zuscum.toFloat())
        }
    }
}