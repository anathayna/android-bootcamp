package com.anathayna.blackboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hello.*

class HelloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        btnFim.setOnClickListener {
            var pronome = ""
            when (rgPronome.checkedRadioButtonId) {
                R.id.rbEla -> pronome = "Ela"
                R.id.rbEle -> pronome = "Ele"
                R.id.rbOutro -> pronome = "Neutro"
            }
        }
    }
}