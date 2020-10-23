package com.anathayna.notes.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.anathayna.notes.R
import com.anathayna.notes.db.AppDatabase
import com.anathayna.notes.model.Note
import kotlinx.android.synthetic.main.activity_new_note.*
import kotlinx.android.synthetic.main.activity_update_note.*
import kotlinx.android.synthetic.main.activity_user.*

class UpdateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        val title = intent.getStringExtra("title")
        val desc = intent.getStringExtra("desc")
        val user = intent.getStringExtra("user")
        var id = intent.getIntExtra("id",0)

        etUpdateTitle.setText(title)
        etUpdateDesc.setText(desc)

        val db = Room.databaseBuilder(this, AppDatabase::class.java, "AppDb").build()

        btnUpdate.setOnClickListener{

        }

        btnDel.setOnClickListener {

            val sharedPref : SharedPreferences =
                getSharedPreferences("Users", Context.MODE_PRIVATE)

            val username : String? = sharedPref.getString("username", "")

            username?.let {
                val note = Note(
                    title = etUpdateTitle.text.toString(),
                    desc = etUpdateDesc.text.toString(),
                    user = it
                )

                Thread {
                    deleteNote(note)
                    finish()
                }.start()
                Toast.makeText(this,"nota excluida", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteNote(note: Note) {
        val db : AppDatabase =
            Room.databaseBuilder(this, AppDatabase::class.java, "AppDb").build()

        db.noteDao().delete(note)
    }
}