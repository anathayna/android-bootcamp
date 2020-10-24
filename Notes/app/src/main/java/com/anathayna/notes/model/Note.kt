package com.anathayna.notes.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    var desc: String,
    var user: String,
    var color: Int?
)