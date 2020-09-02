package com.anathayna.imagine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnGoToDetail.setOnClickListener {
            val i = Intent(this, DetailActivity::class.java)
            startActivity(i)
        }
    }
}