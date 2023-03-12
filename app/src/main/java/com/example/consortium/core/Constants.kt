package com.example.consortium.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object Constants {
    val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "cstj-datastore")
}