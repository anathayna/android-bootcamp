package com.anathayna.notes.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.anathayna.notes.R
import com.anathayna.notes.db.AppDatabase
import com.anathayna.notes.model.Note
import kotlinx.android.synthetic.main.activity_new_note.*


class NewNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        btnAdd.setOnClickListener {
            val sharedPref : SharedPreferences =
                getSharedPreferences("Users", Context.MODE_PRIVATE)

            val username : String? = sharedPref.getString("username", "")

            username?.let {
                val note = Note(
                    title = etTitle.text.toString(),
                    desc = etDescription.text.toString(),
                    user = it
                )

                Thread {
                    saveNote(note)
                    finish()
                }.start()
            }
        }
    }

    fun saveNote(note: Note) {
        val db : AppDatabase =
            Room.databaseBuilder(this, AppDatabase::class.java, "AppDb").build()

        db.noteDao().save(note)
    }
}