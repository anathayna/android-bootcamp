package com.anathayna.blackboard

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_date_time.*
import java.util.*

class DateTimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time)

        val calendar = Calendar.getInstance()
        val ano = calendar.get(Calendar.YEAR)
        val mes = calendar.get(Calendar.MONTH)
        val dia = calendar.get(Calendar.DAY_OF_MONTH)

        etData.setOnClickListener {
            DatePickerDialog(
                this,
                { view, day, mouth, year ->
                    etData.setText("$day/$mouth/$year")
                },
                dia, mes, ano
            ).show()
        }
    }
}