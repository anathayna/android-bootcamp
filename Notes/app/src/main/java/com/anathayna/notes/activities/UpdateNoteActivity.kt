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
            Thread {
                updateNote(
                    etUpdateTitle.text.toString(),
                    etUpdateDesc.text.toString(),
                    id
                )
                finish()
            }.start()
            finish()
        }

        btnDel.setOnClickListener {
            Thread {
                deleteNote(id)
                finish()
            }.start()
            Toast.makeText(this,"nota excluida", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteNote(id: Int){
        val db =
            Room.databaseBuilder(this, AppDatabase::class.java,"AppDb").build()
        db.noteDao().delete(id)
    }

    fun updateNote(title: String, desc: String, id: Int){
        val db =
            Room.databaseBuilder(this, AppDatabase::class.java,"AppDb").build()
        db.noteDao().updateData(title, desc, id)
    }
}