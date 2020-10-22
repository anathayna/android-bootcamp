package com.anathayna.notes.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.preference.PreferenceManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anathayna.notes.R
import com.anathayna.notes.db.AppDatabase
import com.anathayna.notes.model.Note
import com.anathayna.notes.model.Notes
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list_notes.*
import kotlinx.android.synthetic.main.activity_new_note.*
import kotlinx.android.synthetic.main.note_card.*
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

        val sharedPrefs : SharedPreferences = getSharedPreferences("Users", Context.MODE_PRIVATE)
        val username : String? = sharedPrefs.getString("username", "")

        Toast.makeText(this, "logged in as $username", Toast.LENGTH_LONG).show()
        //Snackbar.make(idFab, "logged in as $username", Snackbar.LENGTH_LONG).show()

        refreshNotes()
    }

    fun refreshNotes() {
        Thread {
            val db : AppDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "AppDb").build()

            val allNotes = db.noteDao().getAll()

            runOnUiThread() {
                updateUI(allNotes)
            }

        }.start()
    }

    fun updateUI(noteList: List<Note>) {
        idNoteContainer.removeAllViews()

        val prefs : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val color: Int = prefs.getInt("noteColor", R.color.noteDefaultColor)

        for(note in noteList) {
            val card =
                layoutInflater.inflate(R.layout.note_card, idNoteContainer, false)

            card.txtTitle.text = note.title
            card.txtDescription.text = note.desc
            card.txtUser.text = note.user

            card.setBackgroundColor(color)

            idNoteContainer.addView(card)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.idUser) {
            val i = Intent(this, UserActivity::class.java)
            startActivity(i)
            return true
        }

        if (item.itemId == R.id.idConfig) {
            val i = Intent(this, SettingsActivity::class.java)
            startActivity(i)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}