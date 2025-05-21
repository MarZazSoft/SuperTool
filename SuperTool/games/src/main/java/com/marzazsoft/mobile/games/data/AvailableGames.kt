package com.marzazsoft.mobile.games.data

import com.marzazsoft.mobile.games.models.Game

fun getGamesList(): List<Game> =
    listOf(
        Game(
            title = "Indie Bird",
            posterUrl = "https://microstudio.dev/gilles/indiebird/icon.png",
            gameUrl = "indiebird",
        ),
        Game(
            title = "Fishing Party 2",
            posterUrl = "https://microstudio.dev/gilles/fishing2/icon.png",
            gameUrl = "fishing2",
        ),
    )
