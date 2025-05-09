package com.marzazsoft.supertool.di

import androidx.credentials.CredentialManager
import com.marzazsoft.supertool.data.DataStoreRepository
import com.marzazsoft.supertool.data.dataStore
import com.marzazsoft.supertool.viewModels.MainViewModel
import com.marzazsoft.supertool.viewModels.ProfileViewModel
import com.marzazsoft.supertool.viewModels.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule =
    module {
        single { androidContext().dataStore }
        single {
            DataStoreRepository(
                dataStore = get(),
            )
        }
        single {
            CredentialManager.create(
                context = get(),
            )
        }
        viewModel {
            SplashViewModel(
                dataStoreRepository = get(),
            )
        }
        viewModel {
            MainViewModel(
                dataStoreRepository = get(),
                credentialManager = get(),
            )
        }
        viewModel {
            ProfileViewModel(
                dataStoreRepository = get(),
                credentialManager = get(),
            )
        }
    }
