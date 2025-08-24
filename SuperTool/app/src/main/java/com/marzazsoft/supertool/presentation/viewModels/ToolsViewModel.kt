package com.marzazsoft.supertool.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.marzazsoft.supertool.repositories.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToolsViewModel(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {
    suspend fun getUserName(): String =
        withContext(Dispatchers.IO) {
            dataStoreRepository.getSignInUser()?.displayName.orEmpty()
        }

    suspend fun getIfGuest(): Boolean =
        withContext(Dispatchers.IO) {
            dataStoreRepository.getLogPreference()
        }
}
