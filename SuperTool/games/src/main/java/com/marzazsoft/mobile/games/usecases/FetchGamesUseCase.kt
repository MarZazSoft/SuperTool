package com.marzazsoft.mobile.games.usecases

import com.marzazsoft.mobile.games.models.Game
import com.marzazsoft.mobile.games.repositories.FetchGamesRepository
import com.marzazsoft.mobile.supertool.common.utils.ApiStatus

class FetchGamesUseCase(
    private val fetchGamesRepository: FetchGamesRepository,
) {
    suspend operator fun invoke(): ApiStatus<List<Game>> = fetchGamesRepository.execute()
}
