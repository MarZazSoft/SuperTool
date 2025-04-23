package com.marzazsoft.supertool.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _firebaseAuthResponse = MutableStateFlow(false)
    val firebaseAuthResponse = _firebaseAuthResponse

    fun signInWithGoogleCredentials(
        credentials: AuthCredential,
        action: () -> Unit,
    ) = viewModelScope.launch {
        try {
            auth
                .signInWithCredential(credentials)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG_MTZ", "Logeado con éxito")
                        firebaseAuthResponse.value = true
                    } else {
                        Log.d("TAG_MTZ", "Error al logearse")
                        firebaseAuthResponse.value = false
                    }
                }.addOnFailureListener {
                    Log.d("TAG_MTZ", "Falló al logearse")
                    firebaseAuthResponse.value = false
                }
        } catch (e: Exception) {
            Log.d("TAG_MTZ", "Algo falló - ${e.localizedMessage}")
        }
    }
}
