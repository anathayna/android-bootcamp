package com.anathayna.notes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anathayna.notes.R
import com.anathayna.notes.model.Note
import com.anathayna.notes.model.Notes
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        btnAdd.setOnClickListener {
            val note = Note(etTitle.text.toString(), etDescription.text.toString())
            Notes.noteList.add(note)
            finish()
        }
    }
}