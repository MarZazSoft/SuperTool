package com.marzazsoft.mobile.games.repositories

import com.marzazsoft.mobile.games.apis.FetchGamesApi
import com.marzazsoft.mobile.games.models.Game
import com.marzazsoft.mobile.supertool.common.utils.ApiStatus

class FetchGamesRepository(
    val fetchGamesApi: FetchGamesApi,
) {
    suspend fun execute(): ApiStatus<List<Game>> = fetchGamesApi()
}
