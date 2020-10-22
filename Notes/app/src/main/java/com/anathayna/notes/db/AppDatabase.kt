package com.anathayna.notes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anathayna.notes.model.Note

@Database(entities = arrayOf(Note::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}