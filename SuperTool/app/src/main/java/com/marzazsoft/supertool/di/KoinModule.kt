package com.marzazsoft.supertool.di

import androidx.credentials.CredentialManager
import com.marzazsoft.supertool.data.dataStore
import com.marzazsoft.supertool.presentation.viewModels.MainViewModel
import com.marzazsoft.supertool.presentation.viewModels.ProfileViewModel
import com.marzazsoft.supertool.presentation.viewModels.SplashViewModel
import com.marzazsoft.supertool.presentation.viewModels.ToolsViewModel
import com.marzazsoft.supertool.repositories.DataStoreRepository
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
        viewModel {
            ToolsViewModel(
                dataStoreRepository = get(),
            )
        }
    }
