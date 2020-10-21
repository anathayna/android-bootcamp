package com.anathayna.notes.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anathayna.notes.R
import com.anathayna.notes.model.Note
import com.anathayna.notes.model.Notes
import kotlinx.android.synthetic.main.activity_new_note.*
import kotlinx.android.synthetic.main.activity_user.*

class NewNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        btnAdd.setOnClickListener {
            val sharedPref : SharedPreferences =
                getSharedPreferences("Users", Context.MODE_PRIVATE)

            val username : String? = sharedPref.getString("username", "")

            username?.let {
                val note = Note(etTitle.text.toString(), etDescription.text.toString(), it)
                Notes.noteList.add(note)
                finish()
            }
        }
    }
}