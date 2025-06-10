package com.marzazsoft.supertool.presentation.viewModels

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
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.marzazsoft.mobile.supertool.common.utils.ApiStatus
import com.marzazsoft.supertool.BuildConfig
import com.marzazsoft.supertool.repositories.DataStoreRepository
import com.marzazsoft.supertool.models.SignInMethod
import com.marzazsoft.supertool.models.User
import com.marzazsoft.supertooldesign.utils.TAG_LOG
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
                val googleCredential = GoogleIdTokenCredential.createFrom(credential.data)
                saveLoggedUserIntoFirebaseAuth(
                    GoogleAuthProvider.getCredential(googleCredential.idToken, null),
                )
                saveUser(googleCredential)
                activateSignInFlag()
                _firebaseAuthState.value = ApiStatus.Success(true)
                Log.d(TAG_LOG, SUCCESS_LOG_IN_WITH_GOOGLE)
            } catch (e: GoogleIdTokenParsingException) {
                _firebaseAuthState.value = ApiStatus.Error(ERROR_LOG_IN_WITH_GOOGLE)
                Log.e(TAG_LOG, "$ERROR_LOG_IN_WITH_GOOGLE ${e.localizedMessage}")
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

    private fun saveLoggedUserIntoFirebaseAuth(credential: AuthCredential) {
        FirebaseAuth
            .getInstance()
            .signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG_LOG, SUCCESS_LOGIN_WITH_FIREBASE_AUTH)
                } else {
                    Log.d(TAG_LOG, "$ERROR_LOG_IN_WITH_FIREBASE_AUTH ${task.exception?.message}")
                }
            }
    }

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
        const val SUCCESS_LOGIN_WITH_FIREBASE_AUTH = "Autenticación exitosa con Firebase"
        private const val ERROR_LOG_IN_WITH_GOOGLE = "Error al iniciar sesión con Google:"
        private const val ERROR_LOG_IN_WITH_FIREBASE_AUTH = "Error en autenticación:"
    }
}
