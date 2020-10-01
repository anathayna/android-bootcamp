package com.anathayna.notes.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anathayna.notes.R
import com.anathayna.notes.model.Notes
import kotlinx.android.synthetic.main.activity_list_notes.*
import kotlinx.android.synthetic.main.note_card.view.*

class ListNotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_notes)

        idFab.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        refreshNotes()
    }

    fun refreshNotes() {
        idNoteContainer.removeAllViews()

        for(note in Notes.noteList) {
            val card =
                layoutInflater.inflate(R.layout.note_card, idNoteContainer, false)

            card.txtTitle.text = note.title
            card.txtDescription.text = note.desc

            idNoteContainer.addView(card)
        }
    }
}