package com.marzazsoft.supertool.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val MAIN_NAME = "MainDataStore"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = MAIN_NAME)
