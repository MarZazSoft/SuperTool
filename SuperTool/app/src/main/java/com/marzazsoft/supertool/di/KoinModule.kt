package com.marzazsoft.supertool.di

import com.marzazsoft.supertool.viewModels.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule =
    module {
        viewModelOf(::MainViewModel)
    }
