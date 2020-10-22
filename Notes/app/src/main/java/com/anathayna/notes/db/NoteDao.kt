package com.anathayna.notes.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anathayna.notes.model.Note

@Dao
interface NoteDao {
    @Insert
    fun save(note: Note)

    @Query(value = "select * from Note")
    fun getAll(): List<Note>
}