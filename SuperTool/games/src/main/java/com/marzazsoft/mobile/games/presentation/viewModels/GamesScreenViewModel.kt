package com.marzazsoft.mobile.games.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.marzazsoft.mobile.games.models.Game
import com.marzazsoft.mobile.supertool.common.utils.ApiStatus
import com.marzazsoft.mobile.supertool.common.utils.verifyLoggedUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GamesScreenViewModel(
    val firebaseFireStore: FirebaseFirestore,
) : ViewModel() {
    private val _gamesStatus = MutableStateFlow<ApiStatus<List<Game>>>(ApiStatus.Empty)
    val gamesStatus = _gamesStatus

    fun getGamesList() {
        viewModelScope.launch {
            _gamesStatus.value = ApiStatus.Loading
            if (verifyLoggedUser()) {
                firebaseFireStore
                    .collection(GAMES_COLLECTION)
                    .get()
                    .addOnSuccessListener { data ->
                        var gamesList = mutableListOf<Game>()
                        for (game in data) {
                            gamesList.add(game.toObject(Game::class.java))
                        }
                        _gamesStatus.value = ApiStatus.Success(gamesList)
                    }.addOnFailureListener { error ->
                        _gamesStatus.value = ApiStatus.Error("$ERROR_LOGGED_USER ${error.localizedMessage}")
                    }
            } else {
                _gamesStatus.value = ApiStatus.Error(ERROR_LOGGED_USER)
            }
        }
    }

    private companion object {
        private const val GAMES_COLLECTION = "games"
        private const val ERROR_LOGGED_USER = "Error al obtener registro de inicio de sesión de usuario"
    }
}
