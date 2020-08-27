package com.anathayna.blackboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.activity_tel.*
import kotlinx.android.synthetic.main.activity_tel.btnConcluir
import kotlinx.android.synthetic.main.activity_tel.etNome
import kotlinx.android.synthetic.main.activity_tel.etTel

class TelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tel)

        val itens = arrayOf("Selecione", "Residencial", "Comercial", "Celular", "Outro")

        val adapter  = ArrayAdapter(this, android.R.layout.simple_spinner_item, itens)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spTipo.adapter = adapter

        btnConcluir.setOnClickListener {
            if (!etNome.text.isEmpty() && !etTel.text.isEmpty() && spTipo.selectedItemPosition != 0) {
                val msg = """Nome: ${etNome.text}
                |Telefone: ${etTel.text}
                |Tipo de Telefone: ${spTipo.selectedItem}
                """.trimMargin("|")

                alert("Concluído", msg, this)
            }
            else {
                alert("Erro", "Preencha todos os campos", this)
            }
        }
    }
}