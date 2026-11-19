package com.marzazsoft.mobile.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marzazsoft.mobile.notes.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "notes_database"
    }
}
