package com.anathayna.notes.model
import androidx.room.Entity

@Entity
data class Note(
    var title: String,
    var desc: String,
    var user: String
)