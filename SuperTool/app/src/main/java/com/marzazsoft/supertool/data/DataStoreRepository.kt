package com.marzazsoft.supertool.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.marzazsoft.supertool.models.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreRepository(
    private val dataStore: DataStore<Preferences>,
) {
    private val isLogged = booleanPreferencesKey(LOGGED_KEY)
    private val logInUser = stringPreferencesKey(USER_KEY)

    suspend fun saveLogPreference(value: Boolean) {
        dataStore.edit { preference ->
            preference[isLogged] = value
        }
    }

    suspend fun getLogPreference(): Boolean =
        dataStore.data
            .map { preference ->
                preference[isLogged] == true
            }.first()

    suspend fun saveSignInUser(user: User?) {
        val gson = Gson()
        val jsonUser = gson.toJson(user)

        dataStore.edit { preferences ->
            preferences[logInUser] = jsonUser
        }
    }

    suspend fun deleteSignInUser() {
        dataStore.edit { preferences ->
            preferences.remove(logInUser)
        }
    }

    suspend fun getSignInUser(): User? {
        val gson = Gson()

        val jsonUser =
            dataStore.data
                .map { preference ->
                    preference[logInUser]
                }.first()

        return if (jsonUser != null) gson.fromJson(jsonUser, User::class.java) else null
    }

    companion object {
        private const val LOGGED_KEY = "LogIn"
        private const val USER_KEY = "User"
    }
}
