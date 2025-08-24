package com.marzazsoft.mobile.games.apis

import com.google.firebase.firestore.FirebaseFirestore
import com.marzazsoft.mobile.games.models.Game
import com.marzazsoft.mobile.supertool.common.utils.ApiStatus
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FetchGamesApi(
    val firebaseFireStore: FirebaseFirestore,
) {
    suspend operator fun invoke(): ApiStatus<List<Game>> =
        suspendCoroutine { coroutine ->
            firebaseFireStore
                .collection(GAMES_COLLECTION)
                .get()
                .addOnSuccessListener { data ->
                    var gamesList =
                        data.mapNotNull {
                            it.toObject(Game::class.java).takeIf { game -> game.visible }
                        }
                    coroutine.resume(ApiStatus.Success(gamesList))
                }.addOnFailureListener { error ->
                    coroutine.resume(
                        ApiStatus.Error("$ERROR_LOGGED_USER ${error.localizedMessage}"),
                    )
                }
        }

    private companion object {
        private const val GAMES_COLLECTION = "games"
        private const val ERROR_LOGGED_USER = "Error al obtener registro de inicio de sesión de usuario"
    }
}
