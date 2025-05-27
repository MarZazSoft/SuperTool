package com.marzazsoft.mobile.games.data

import com.marzazsoft.mobile.games.models.Game

fun getGamesList(): List<Game> =
    listOf(
        Game(
            title = "Indie Bird",
            author = "Gilles",
            posterUrl = "https://microstudio.dev/gilles/indiebird/icon.png",
            gameUrl = "https://microstudio.io/gilles/indiebird/",
        ),
        Game(
            title = "Fishing Party 2",
            author = "Gilles",
            posterUrl = "https://microstudio.dev/gilles/fishing2/icon.png",
            gameUrl = "https://microstudio.io/gilles/fishing2/",
        ),
        Game(
            title = "Racing Demo",
            author = "Gilles",
            posterUrl = "https://microstudio.dev/gilles/racingdemo/icon.png",
            gameUrl = "https://microstudio.io/gilles/racingdemo/",
        ),
        Game(
            title = "Rope Climber",
            author = "Super Ankan",
            posterUrl = "https://microstudio.dev/super_ankan/rope_climber/icon.png",
            gameUrl = "https://microstudio.io/super_ankan/rope_climber/",
        ),
        Game(
            title = "Skate Run",
            author = "Gilles",
            posterUrl = "https://microstudio.dev/gilles/skaterun/icon.png",
            gameUrl = "https://microstudio.io/gilles/skaterun/",
        ),
        Game(
            title = "One Bit Racer",
            author = "JimB007",
            posterUrl = "https://microstudio.dev/JimB007/onebitracer//icon.png",
            gameUrl = "https://microstudio.io/JimB007/onebitracer/",
        ),
        Game(
            title = "Fake Geometry",
            author = "Viaan",
            posterUrl = "https://microstudio.dev/Viaan/viaansetheducationnswgovau/icon.png",
            gameUrl = "https://microstudio.io/Viaan/viaansetheducationnswgovau/",
        ),
        Game(
            title = "Fruit Slicer",
            author = "Homine Ludens",
            posterUrl = "https://microstudio.dev/HomineLudens/fruitslicer/icon.png",
            gameUrl = "https://microstudio.io/HomineLudens/fruitslicer/",
        ),
    )
