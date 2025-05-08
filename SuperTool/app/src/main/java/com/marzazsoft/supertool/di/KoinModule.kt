package com.marzazsoft.supertool.di

import com.marzazsoft.supertool.data.DataStoreRepository
import com.marzazsoft.supertool.data.dataStore
import com.marzazsoft.supertool.viewModels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule =
    module {
        viewModelOf(::MainViewModel)
        single { androidContext().dataStore }
        single { DataStoreRepository(get()) }
    }
