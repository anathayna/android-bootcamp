package com.anathayna.notes.activities

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.anathayna.notes.R
import com.anathayna.notes.db.AppDatabase
import com.anathayna.notes.model.Note
import kotlinx.android.synthetic.main.activity_new_note.*
import kotlinx.android.synthetic.main.activity_update_note.*
import kotlinx.android.synthetic.main.activity_user.*
import petrov.kristiyan.colorpicker.ColorPicker

class UpdateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val color = prefs.getInt("noteColor", R.color.noteDefaultColor)
        var cor = intent.getIntExtra("color", color)

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
                    cor,
                    id
                )
                finish()
            }.start()
            Toast.makeText(this,"nota atualizada", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnDel.setOnClickListener {
            Thread {
                deleteNote(id)
                finish()
            }.start()
            Toast.makeText(this,"nota excluida", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnUpdateColor.setOnClickListener {
            val colorPicker = ColorPicker(this)
            if (cor == color) {
                colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                    override fun onChooseColor(position: Int, color: Int) {
                        if(color==0) cor = -14654801
                        else cor = color
                    }

                    override fun onCancel() {
                        TODO("Not yet implemented")
                    }
                }).setRoundColorButton(true)
                    .setColumns(5)
                    .setDefaultColorButton(Color.parseColor("#2062af"))
                    .show()
            }
            else {
                colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                    override fun onChooseColor(position: Int, color: Int) {
                        if(color!=0) cor = color
                    }

                    override fun onCancel() {
                        TODO("Not yet implemented")
                    }
                }).setRoundColorButton(true)
                    .setColumns(5)
                    .setDefaultColorButton(cor)
                    .show()
            }
        }
    }

    fun deleteNote(id: Int){
        val db =
            Room.databaseBuilder(this, AppDatabase::class.java,"AppDb").build()
        db.noteDao().delete(id)
    }

    fun updateNote(title: String, desc: String, color: Int?, id: Int){
        val db =
            Room.databaseBuilder(this, AppDatabase::class.java,"AppDb").build()
        db.noteDao().updateData(title, desc, color, id)
    }
}