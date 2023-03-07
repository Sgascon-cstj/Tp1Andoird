package com.example.consortium.domain.repositories

import android.content.Context
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

class TraderRepository(private val context: Context){
    object PreferencesKeys {
        val USERNAME = stringPreferencesKey("username")
        val QUANTITY_ELEMENT_FR = floatPreferencesKey("element_FR")
        val QUANTITY_ELEMENT_K = floatPreferencesKey("element_K")
        val QUANTITY_ELEMENT_VE = floatPreferencesKey("element_VE")
        val QUANTITY_ELEMENT_YE = floatPreferencesKey("element_YE")
        val QUANTITY_ELEMENT_Z = floatPreferencesKey("element_Z")
    }
}