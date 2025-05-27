package com.marzazsoft.supertool.presentation.viewModels

import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import com.marzazsoft.supertool.data.DataStoreRepository
import com.marzazsoft.supertool.utils.TAG_LOG
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

    suspend fun getUserName(): String =
        withContext(Dispatchers.IO) {
            Log.d(TAG_LOG, "Este es el token -> ${dataStoreRepository.getSignInUser()?.idToken}")
            dataStoreRepository.getSignInUser()?.displayName.orEmpty()
        }

    suspend fun getEmail(): String =
        withContext(Dispatchers.IO) {
            dataStoreRepository.getSignInUser()?.id.orEmpty()
        }

    suspend fun getProfileImage(): String =
        withContext(Dispatchers.IO) {
            dataStoreRepository
                .getSignInUser()
                ?.profilePictureUri
                ?.substringBefore("\\")
                .orEmpty()
        }
}
