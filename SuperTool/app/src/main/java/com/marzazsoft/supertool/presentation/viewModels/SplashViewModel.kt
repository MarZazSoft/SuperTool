package com.marzazsoft.supertool.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.marzazsoft.supertool.data.DataStoreRepository
import kotlinx.coroutines.Dispatchers

class SplashViewModel(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {
    suspend fun isLogged() =
        with(Dispatchers.IO) {
            dataStoreRepository.getLogPreference()
        }
}
