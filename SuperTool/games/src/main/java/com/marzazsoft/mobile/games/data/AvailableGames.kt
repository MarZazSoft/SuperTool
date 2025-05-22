package com.marzazsoft.mobile.games.data

import com.marzazsoft.mobile.games.models.Game

fun getGamesList(): List<Game> =
    listOf(
        Game(
            title = "Indie Bird",
            posterUrl = "https://microstudio.dev/gilles/indiebird/icon.png",
            gameUrl = "https://microstudio.io/gilles/indiebird/",
        ),
        Game(
            title = "Fishing Party 2",
            posterUrl = "https://microstudio.dev/gilles/fishing2/icon.png",
            gameUrl = "https://microstudio.io/gilles/fishing2/",
        ),
        Game(
            title = "Racing Demo",
            posterUrl = "https://microstudio.dev/gilles/racingdemo/icon.png",
            gameUrl = "https://microstudio.io/gilles/racingdemo/",
        ),
        Game(
            title = "Rope Climber",
            posterUrl = "https://microstudio.dev/super_ankan/rope_climber/icon.png",
            gameUrl = "https://microstudio.io/super_ankan/rope_climber/",
        ),
        Game(
            title = "Skate Run",
            posterUrl = "https://microstudio.dev/gilles/skaterun/icon.png",
            gameUrl = "https://microstudio.io/gilles/skaterun/",
        ),
        Game(
            title = "One Bit Racer",
            posterUrl = "https://microstudio.dev/JimB007/onebitracer//icon.png",
            gameUrl = "https://microstudio.io/JimB007/onebitracer/",
        ),
        Game(
            title = "Fake Geometry",
            posterUrl = "https://microstudio.dev/Viaan/viaansetheducationnswgovau/icon.png",
            gameUrl = "https://microstudio.io/Viaan/viaansetheducationnswgovau/",
        ),
        Game(
            title = "Subway surfers",
            posterUrl = "https://microstudio.dev/HomineLudens/fruitslicer/icon.png",
            gameUrl = "https://microstudio.io/HomineLudens/fruitslicer/",
        ),
    )
