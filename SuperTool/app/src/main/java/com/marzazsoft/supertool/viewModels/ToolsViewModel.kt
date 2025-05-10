package com.marzazsoft.supertool.viewModels

import androidx.lifecycle.ViewModel
import com.marzazsoft.supertool.data.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToolsViewModel(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {
    suspend fun getUserName(): String =
        withContext(Dispatchers.IO) {
            dataStoreRepository.getSignInUser()?.givenName.orEmpty()
        }
}
