package com.marzazsoft.supertool.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepository(
    private val dataStore: DataStore<Preferences>,
) {
    private val isLogged = booleanPreferencesKey(LOGGED_KEY)

    suspend fun saveLogPreference(value: Boolean) {
        dataStore.edit { preference ->
            preference[isLogged] = value
        }
    }

    fun getLogPreference(): Flow<Boolean> =
        dataStore.data.map { preference ->
            preference[isLogged] ?: false
        }

    companion object {
        const val LOGGED_KEY = "LogIn"
    }
}
