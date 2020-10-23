package com.anathayna.notes.db
import androidx.room.*
import com.anathayna.notes.model.Note

@Dao
interface NoteDao {
    @Insert
    fun save(note: Note)

    @Query(value = "select * from Note")
    fun getAll(): List<Note>

    @Query("delete from Note where id = :id")
    fun delete(id: Int)
}