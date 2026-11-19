package com.marzazsoft.mobile.radio.di

import com.marzazsoft.mobile.radio.presentation.viewModels.RadioViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val radioLibraryModule =
    module {
        viewModel {
            RadioViewModel(context = androidContext())
        }
    }
