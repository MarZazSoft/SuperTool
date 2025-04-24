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
import com.marzazsoft.supertool.utils.TAG_LOG
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _firebaseAuthResponse = MutableStateFlow(false)
    val firebaseAuthResponse = _firebaseAuthResponse

    fun getGoogleSignInOptions(): GoogleSignInOptions =
        GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.WEB_ID)
            .requestEmail()
            .build()

    fun signInWithGoogleCredentials(credentials: AuthCredential) =
        viewModelScope.launch {
            try {
                auth
                    .signInWithCredential(credentials)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG_LOG, SUCCESS_LOG_IN_WITH_GOOGLE)
                            firebaseAuthResponse.value = true
                        } else {
                            Log.d(TAG_LOG, ERROR_LOG_IN_WITH_GOOGLE)
                            firebaseAuthResponse.value = false
                        }
                    }.addOnFailureListener {
                        Log.d(TAG_LOG, "$ERROR_LOG_IN_WITH_GOOGLE $it")
                        firebaseAuthResponse.value = false
                    }
            } catch (e: Exception) {
                Log.d(TAG_LOG, "$ERROR_LOG_IN_WITH_GOOGLE ${e.localizedMessage}")
            }
        }

    companion object {
        private const val SUCCESS_LOG_IN_WITH_GOOGLE = "Inicio de sesión con éxito"
        private const val ERROR_LOG_IN_WITH_GOOGLE = "Error al iniciar sesión con Google"
    }
}
