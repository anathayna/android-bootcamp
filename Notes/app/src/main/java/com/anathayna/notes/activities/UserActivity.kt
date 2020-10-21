package com.anathayna.notes.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anathayna.notes.R
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val sharedPrefs : SharedPreferences =
            getSharedPreferences("Users", Context.MODE_PRIVATE)

        etUsername.setText(sharedPrefs.getString("username", ""))

        btnLogin.setOnClickListener {

            val editor : SharedPreferences.Editor = sharedPrefs.edit()
            val username : String = etUsername.text.toString()
            editor.putString("username", username)
            
            editor.commit()
        }
    }
}