package com.example.listit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getCurrentUser() == null) {
            val providers = arrayListOf(
                    AuthUI.IdpConfig.EmailBuilder().build(),
                    AuthUI.IdpConfig.GoogleBuilder().build())

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(), 0
            )
        } else {
            Toast.makeText(this, "authenticado", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 0) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this, "authenticado", Toast.LENGTH_LONG).show()
            } else {
                finishAffinity()
            }
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser
    }
}