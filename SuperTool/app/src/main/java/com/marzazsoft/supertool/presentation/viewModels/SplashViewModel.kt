package com.marzazsoft.supertool.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.marzazsoft.supertool.data.DataStoreRepository

class SplashViewModel(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {
    fun isLogged() = dataStoreRepository.getLogPreference()
}
