package com.marzazsoft.mobile.notes.usecases

import com.marzazsoft.mobile.notes.models.Note
import com.marzazsoft.mobile.notes.repositories.NotesRepository

class NotesUseCase(private val notesRepository: NotesRepository) {
    suspend fun getAllNotes(): List<Note> = notesRepository.getAllNotes()

    suspend fun getNoteById(id: Int): Note? = notesRepository.getNoteById(id)

    suspend fun saveNote(note: Note) = notesRepository.saveNote(note)

    suspend fun updateNote(note: Note) = notesRepository.updateNote(note)

    suspend fun deleteNote(note: Note) = notesRepository.deleteNote(note)
}
