package com.example.expenseapplication.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.expenseapplication.Constants.Constants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow
import java.util.prefs.Preferences




class PinDataStore(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "pinDataStore")
        private val PIN_KEY = stringPreferencesKey(Constants.PIN_KEY)
    }

    suspend fun savePin(pin: String) {
        context.dataStore.edit { preferences ->
            preferences[PIN_KEY] = pin.toString()
        }
    }

    suspend fun retrievePin(): String? {
        val preferences = context.dataStore.data.first()
        return preferences[PIN_KEY]
    }

    val pinFlow  = context.dataStore.data.map {
        it[PIN_KEY]
    }
}
