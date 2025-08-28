package br.edu.ifsp.scl.ads.prdm.sc3015467.calculadora

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3015467.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var operand1: Double? = null
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(amb.root)

        with(amb) {
            button1.setOnClickListener { appendToResult("1") }
            button2.setOnClickListener { appendToResult("2") }
            button3.setOnClickListener { appendToResult("3") }
            buttonAdd.setOnClickListener { setOperator("+") }
            button4.setOnClickListener { appendToResult("4") }
            button5.setOnClickListener { appendToResult("5") }
            button6.setOnClickListener { appendToResult("6") }
            buttonSubtract.setOnClickListener { setOperator("-") }
            button7.setOnClickListener { appendToResult("7") }
            button8.setOnClickListener { appendToResult("8") }
            button9.setOnClickListener { appendToResult("9") }
            buttonMultiply.setOnClickListener { setOperator("*") }
            button0.setOnClickListener { appendToResult("0") }
            buttonEquals.setOnClickListener { calculateResult() }
            buttonDivide.setOnClickListener { setOperator("/") }
            buttonClear.setOnClickListener { clear() }
        }
    }

    private fun appendToResult(value: String) {
        amb.editTextResult.append(value)
    }

    private fun setOperator(op: String) {
        if (amb.editTextResult.text.isNotEmpty()) {
            operand1 = amb.editTextResult.text.toString().toDoubleOrNull()
            operator = op

            amb.editTextResult.text.clear()
        } else {
            operator = op
        }
    }

    private fun calculateResult() {
        val operand2 = amb.editTextResult.text.toString().toDoubleOrNull()
        if (operand1 == null || operand2 == null || operator == null) {
            Toast.makeText(this, "Operação inválida", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (operator) {
            "+" -> operand1!! + operand2
            "-" -> operand1!! - operand2
            "*" -> operand1!! * operand2
            "/" -> if (operand2 == 0.0) {
                Toast.makeText(this, "Divisão por zero não é permitida", Toast.LENGTH_SHORT).show()
                return
            } else {
                operand1!! / operand2
            }
            else -> null
        }
        amb.editTextResult.setText(result.toString())
    }

    private fun clear() {
        amb.editTextResult.text.clear()
        operand1 = null
        operator = null
    }
}