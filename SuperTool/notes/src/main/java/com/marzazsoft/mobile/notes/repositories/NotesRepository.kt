package com.marzazsoft.mobile.notes.repositories

import com.marzazsoft.mobile.notes.data.NoteDao
import com.marzazsoft.mobile.notes.models.Note

class NotesRepository(private val noteDao: NoteDao) {
    suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes()

    suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id)

    suspend fun saveNote(note: Note) = noteDao.insertNote(note)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
}
