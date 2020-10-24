package com.anathayna.notes.activities

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.anathayna.notes.R
import com.anathayna.notes.db.AppDatabase
import com.anathayna.notes.model.Note
import kotlinx.android.synthetic.main.activity_new_note.*
import petrov.kristiyan.colorpicker.ColorPicker


class NewNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        var cor: Int? = null

        btnAdd.setOnClickListener {
            val sharedPref : SharedPreferences =
                getSharedPreferences("Users", Context.MODE_PRIVATE)

            val username : String? = sharedPref.getString("username", "")

            username?.let {
                val note = Note(
                    title = etTitle.text.toString(),
                    desc = etDescription.text.toString(),
                    color = cor,
                    user = it
                )

                Thread {
                    saveNote(note)
                    finish()
                }.start()
            }
        }

        btnColor.setOnClickListener {
            val colorPicker = ColorPicker(this)

            if(cor != null) {
                colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                    override fun onChooseColor(position: Int, color: Int) {
                        if(color != 0)  cor = color
                    }

                    override fun onCancel() {
                        TODO("Not yet implemented")
                    }
                }).setRoundColorButton(true)
                    .setColumns(5)
                    .setDefaultColorButton(cor!!)
                    .show()
            }
            else {
                colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                    override fun onChooseColor(position: Int, color: Int) {
                        cor = color
                        if(color == 0) cor = -14654801
                    }

                    override fun onCancel() {
                        TODO("Not yet implemented")
                    }
                }).setRoundColorButton(true)
                    .setColumns(5)
                    .setDefaultColorButton(Color.parseColor("#2062af"))
                    .show()
            }
        }
    }

    fun saveNote(note: Note) {
        val db : AppDatabase =
            Room.databaseBuilder(this, AppDatabase::class.java, "AppDb").build()

        db.noteDao().save(note)
    }
}