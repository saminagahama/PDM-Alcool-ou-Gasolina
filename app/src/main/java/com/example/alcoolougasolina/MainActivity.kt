package com.example.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var edAlcool: EditText
    private lateinit var edGasolina: EditText
    private lateinit var swPercentual: Switch
    private lateinit var resultadoTextView: TextView

    private var percentual: Double = 0.7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edAlcool = findViewById(R.id.edGasolina)
        edGasolina = findViewById(R.id.edAlcool)
        swPercentual = findViewById(R.id.swPercentual)
        resultadoTextView = findViewById(R.id.resultado)

        if (savedInstanceState != null) {
            percentual = savedInstanceState.getDouble("percentual")
        }

        swPercentual.isChecked = (percentual == 0.75)

        val btCalc: Button = findViewById(R.id.btCalcular)
        btCalc.setOnClickListener(View.OnClickListener {
            val alcoolPrice = edAlcool.text.toString().toDoubleOrNull()
            val gasolinaPrice = edGasolina.text.toString().toDoubleOrNull()

            if (alcoolPrice != null && gasolinaPrice != null) {
                val result = calculateFuel(alcoolPrice, gasolinaPrice, percentual)
                displayResult(result)
            } else {
                resultadoTextView.text = "Preencha os valores de álcool e gasolina."
            }
        })

        swPercentual.setOnCheckedChangeListener { _, isChecked ->
            percentual = if (isChecked) 0.75 else 0.7
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("percentual", percentual)
    }

    private fun calculateFuel(alcoolPrice: Double, gasolinaPrice: Double, percentual: Double): String {
        val ratio = gasolinaPrice / alcoolPrice

        return if (ratio < percentual) {
            "Álcool é mais vantajoso."
        } else {
            "Gasolina é mais vantajosa."
        }
    }

    private fun displayResult(result: String) {
        resultadoTextView.text = result
    }
}
