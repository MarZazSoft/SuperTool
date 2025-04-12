package com.marzazsoft.supertool.di

import com.marzazsoft.supertool.viewModels.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule =
    module {
        viewModelOf(::LoginViewModel)
    }
