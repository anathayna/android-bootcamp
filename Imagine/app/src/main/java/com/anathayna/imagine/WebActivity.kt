package com.anathayna.imagine

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        btnGoToUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sp.senac.br/"))
            startActivity(intent)
        }
    }
}