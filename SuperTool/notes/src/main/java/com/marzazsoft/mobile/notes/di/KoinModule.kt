package com.marzazsoft.mobile.notes.di

import androidx.room.Room
import com.marzazsoft.mobile.notes.data.NotesDatabase
import com.marzazsoft.mobile.notes.presentation.viewModels.NotesViewModel
import com.marzazsoft.mobile.notes.repositories.NotesRepository
import com.marzazsoft.mobile.notes.usecases.NotesUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val notesLibraryModule =
    module {
        single {
            Room
                .databaseBuilder(
                    androidContext(),
                    NotesDatabase::class.java,
                    NotesDatabase.DATABASE_NAME,
                ).build()
        }
        single { get<NotesDatabase>().noteDao() }
        single { NotesRepository(noteDao = get()) }
        single { NotesUseCase(notesRepository = get()) }
        viewModel { NotesViewModel(notesUseCase = get()) }
    }
