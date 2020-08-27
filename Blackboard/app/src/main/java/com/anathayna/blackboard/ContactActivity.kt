package com.anathayna.blackboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        btnConcluir.setOnClickListener {
            var msg = """Nome: ${etNome.text}
                |Telefone: ${etTel.text}
                |Email: ${etEmail.text}
                |
                |Preferência de Contato
            """.trimMargin("|")

            if (cbTel.isChecked) {
                msg += "\n - Telefone"
            }

            if (cbEmail.isChecked) {
                msg += "\n - Email"
            }

            alert("Dados", msg, this)
        }
    }
}