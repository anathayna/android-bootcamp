package com.anathayna.blackboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_tel.*

class TelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tel)

        val itens = arrayOf("Selecione", "Residencial", "Comercial", "Celular", "Outro")

        val adapter  = ArrayAdapter(this, android.R.layout.simple_spinner_item, itens)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spTipo.adapter = adapter
    }
}