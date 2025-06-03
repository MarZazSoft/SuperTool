package com.marzazsoft.mobile.games.di

import com.google.firebase.firestore.FirebaseFirestore
import com.marzazsoft.mobile.games.apis.FetchGamesApi
import com.marzazsoft.mobile.games.presentation.viewModels.GamesScreenViewModel
import com.marzazsoft.mobile.games.repositories.FetchGamesRepository
import com.marzazsoft.mobile.games.usecases.FetchGamesUseCase
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val gamesLibraryModule =
    module {
        single {
            HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(Json { ignoreUnknownKeys = true })
                }
            }
        }
        single {
            FirebaseFirestore.getInstance()
        }
        single {
            FetchGamesApi(firebaseFireStore = get())
        }
        single {
            FetchGamesRepository(fetchGamesApi = get())
        }
        single {
            FetchGamesUseCase(fetchGamesRepository = get())
        }
        viewModel {
            GamesScreenViewModel(
                fetchGamesUseCase = get(),
            )
        }
    }
