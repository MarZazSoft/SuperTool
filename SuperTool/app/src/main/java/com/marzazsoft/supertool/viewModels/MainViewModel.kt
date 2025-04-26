package com.marzazsoft.supertool.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.marzazsoft.supertool.BuildConfig
import com.marzazsoft.supertool.utils.ApiStatus
import com.marzazsoft.supertool.utils.TAG_LOG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Suppress("ktlint:standard:backing-property-naming")
class MainViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    private val _firebaseAuthState = MutableStateFlow<ApiStatus<Boolean>>(ApiStatus.Empty)
    val firebaseAuthState = _firebaseAuthState

    fun getGoogleSignInOptions(): GoogleSignInOptions {
        _firebaseAuthState.value = ApiStatus.Loading
        return GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.WEB_ID)
            .requestEmail()
            .build()
    }

    fun signInWithGoogleCredentials(credentials: AuthCredential) =
        viewModelScope.launch {
            try {
                auth
                    .signInWithCredential(credentials)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _firebaseAuthState.value = ApiStatus.Success(true)
                            Log.d(TAG_LOG, SUCCESS_LOG_IN_WITH_GOOGLE)
                        } else {
                            _firebaseAuthState.value = ApiStatus.Error(ERROR_LOG_IN_WITH_GOOGLE)
                            Log.d(TAG_LOG, ERROR_LOG_IN_WITH_GOOGLE)
                        }
                    }.addOnFailureListener {
                        _firebaseAuthState.value = ApiStatus.Error("$ERROR_LOG_IN_WITH_GOOGLE $it")
                        Log.d(TAG_LOG, "$ERROR_LOG_IN_WITH_GOOGLE $it")
                    }
            } catch (e: Exception) {
                _firebaseAuthState.value = ApiStatus.Error("$ERROR_LOG_IN_WITH_GOOGLE ${e.localizedMessage}")
                Log.d(TAG_LOG, "$ERROR_LOG_IN_WITH_GOOGLE ${e.localizedMessage}")
            }
        }

    companion object {
        private const val SUCCESS_LOG_IN_WITH_GOOGLE = "Inicio de sesión con éxito"
        const val ERROR_LOG_IN_WITH_GOOGLE = "Error al iniciar sesión con Google"
    }
}
