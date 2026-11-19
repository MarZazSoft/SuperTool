package com.marzazsoft.mobile.radio.presentation.viewModels

import android.content.ComponentName
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.marzazsoft.mobile.radio.service.RadioPlaybackService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RadioViewModel(private val context: Context) : ViewModel() {
    private val _player = MutableStateFlow<Player?>(null)
    val player: StateFlow<Player?> = _player.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    private var controllerFuture: ListenableFuture<MediaController>? = null

    init {
        val sessionToken = SessionToken(context, ComponentName(context, RadioPlaybackService::class.java))
        controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture!!.addListener(
            {
                try {
                    val controller = controllerFuture?.get()
                    controller?.addListener(
                        object : Player.Listener {
                            override fun onPlayerError(error: PlaybackException) {
                                _errorState.value = error.localizedMessage ?: "Error de reproducción desconocido"
                            }
                        },
                    )
                    _player.value = controller
                } catch (e: Exception) {
                    _errorState.value = e.localizedMessage ?: "Error al conectar con el servicio de radio"
                }
            },
            MoreExecutors.directExecutor(),
        )
    }

    fun clearError() {
        _errorState.value = null
    }

    override fun onCleared() {
        controllerFuture?.let { MediaController.releaseFuture(it) }
        super.onCleared()
    }
}
