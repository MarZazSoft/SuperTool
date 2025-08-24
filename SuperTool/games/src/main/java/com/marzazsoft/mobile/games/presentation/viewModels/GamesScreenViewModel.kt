package com.marzazsoft.mobile.games.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marzazsoft.mobile.games.models.Game
import com.marzazsoft.mobile.games.usecases.FetchGamesUseCase
import com.marzazsoft.mobile.supertool.common.utils.ApiStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GamesScreenViewModel(
    val fetchGamesUseCase: FetchGamesUseCase,
) : ViewModel() {
    private val _gamesStatus = MutableStateFlow<ApiStatus<List<Game>>>(ApiStatus.Loading)
    val gamesStatus = _gamesStatus

    fun getGamesList() =
        viewModelScope.launch {
            _gamesStatus.value = fetchGamesUseCase()
        }
}
