package com.marzazsoft.supertool.viewModels

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.marzazsoft.supertool.BuildConfig
import com.marzazsoft.supertool.data.DataStoreRepository
import com.marzazsoft.supertool.models.SignInMethod
import com.marzazsoft.supertool.models.User
import com.marzazsoft.supertool.utils.ApiStatus
import com.marzazsoft.supertool.utils.TAG_LOG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

@Suppress("ktlint:standard:backing-property-naming")
class MainViewModel(
    private val dataStoreRepository: DataStoreRepository,
    private val credentialManager: CredentialManager,
) : ViewModel() {
    private val _firebaseAuthState = MutableStateFlow<ApiStatus<Boolean>>(ApiStatus.Empty)
    val firebaseAuthState = _firebaseAuthState

    suspend fun goToGoogleSignIn(
        credentialManager: CredentialManager,
        context: Context,
    ) = coroutineScope {
        withContext(Dispatchers.Default) {
            try {
                val result =
                    credentialManager.getCredential(
                        request = setUpGoogleSignIn(),
                        context = context,
                    )
                handleSignInResponse(result)
            } catch (e: GetCredentialException) {
                Log.d(TAG_LOG, "$ERROR_LOG_IN_WITH_GOOGLE - ${e.localizedMessage}")
            }
        }
    }

    private fun setUpGoogleSignIn(): GetCredentialRequest {
        val signInWithGoogleOption: GetSignInWithGoogleOption =
            GetSignInWithGoogleOption
                .Builder(BuildConfig.WEB_ID)
                .setNonce(SUCCESS_LOG_IN_WITH_GOOGLE)
                .build()

        return GetCredentialRequest
            .Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()
    }

    private suspend fun handleSignInResponse(result: GetCredentialResponse) {
        _firebaseAuthState.value = ApiStatus.Loading
        when (val credential = result.credential) {
            is CustomCredential -> onSuccessGoogleSignIn(credential)
            else -> onFailureGoogleSignIn()
        }
    }

    private suspend fun onSuccessGoogleSignIn(credential: Credential) {
        if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {
                saveUser(GoogleIdTokenCredential.createFrom(credential.data))
                activateSignInFlag()
                _firebaseAuthState.value = ApiStatus.Success(true)
                Log.d(TAG_LOG, SUCCESS_LOG_IN_WITH_GOOGLE)
            } catch (e: GoogleIdTokenParsingException) {
                _firebaseAuthState.value = ApiStatus.Error(ERROR_LOG_IN_WITH_GOOGLE)
                Log.e(TAG_LOG, "$ERROR_LOG_IN_WITH_GOOGLE - ${e.localizedMessage}")
            }
        } else {
            _firebaseAuthState.value = ApiStatus.Error(ERROR_LOG_IN_WITH_GOOGLE)
            Log.e(TAG_LOG, ERROR_LOG_IN_WITH_GOOGLE)
        }
    }

    private fun onFailureGoogleSignIn() {
        _firebaseAuthState.value = ApiStatus.Error(ERROR_LOG_IN_WITH_GOOGLE)
        Log.e(TAG_LOG, ERROR_LOG_IN_WITH_GOOGLE)
    }

    private suspend fun activateSignInFlag() =
        withContext(Dispatchers.IO) {
            dataStoreRepository.saveLogPreference(true)
        }

    fun getCredentialManager() = credentialManager

    private suspend fun saveUser(googleTokenIdCredential: GoogleIdTokenCredential) =
        withContext(Dispatchers.IO) {
            val user =
                with(googleTokenIdCredential) {
                    User(
                        displayName = displayName,
                        familyName = familyName,
                        givenName = givenName,
                        id = id,
                        idToken = idToken,
                        phoneNumber = phoneNumber,
                        profilePictureUri = profilePictureUri.toString(),
                        signInWith = SignInMethod.GOOGLE,
                    )
                }

            dataStoreRepository.saveSignInUser(user)
        }

    companion object {
        const val SUCCESS_LOG_IN_WITH_GOOGLE = "Inicio de sesión con éxito"
        private const val ERROR_LOG_IN_WITH_GOOGLE = "Error al iniciar sesión con Google"
    }
}
