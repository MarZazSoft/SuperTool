package com.marzazsoft.supertool.viewModels

import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import com.marzazsoft.supertool.data.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val dataStoreRepository: DataStoreRepository,
    private val credentialManager: CredentialManager,
) : ViewModel() {
    suspend fun resetSignInPreference() {
        withContext(Dispatchers.IO) {
            with(dataStoreRepository) {
                saveLogPreference(false)
                deleteSignInUser()
            }
        }
    }

    suspend fun clearCredentialManager() {
        withContext(Dispatchers.Default) {
            credentialManager.clearCredentialState(
                ClearCredentialStateRequest(),
            )
        }
    }
}
