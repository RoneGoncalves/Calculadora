package br.edu.ifsp.scl.ads.prdm.sc3015467.calculadora

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3015467.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var currentValue: String = ""
    private var operand1: Double? = null
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        with(amb) {
            button1.setOnClickListener { appendToCurrentValue("1") }
            button2.setOnClickListener { appendToCurrentValue("2") }
            button3.setOnClickListener { appendToCurrentValue("3") }
            buttonAdd.setOnClickListener { setOperator("+") }
            button4.setOnClickListener { appendToCurrentValue("4") }
            button5.setOnClickListener { appendToCurrentValue("5") }
            button6.setOnClickListener { appendToCurrentValue("6") }
            buttonSubtract.setOnClickListener { setOperator("-") }
            button7.setOnClickListener { appendToCurrentValue("7") }
            button8.setOnClickListener { appendToCurrentValue("8") }
            button9.setOnClickListener { appendToCurrentValue("9") }
            buttonMultiply.setOnClickListener { setOperator("*") }
            button0.setOnClickListener { appendToCurrentValue("0") }
            buttonEquals.setOnClickListener { calculateResult() }
            buttonDivide.setOnClickListener { setOperator("/") }
            buttonClear.setOnClickListener { clear() }
        }
    }

    private fun appendToCurrentValue(value: String) {
        currentValue += value
        amb.editTextResult.setText(currentValue)
    }

    private fun setOperator(op: String) {
        if (currentValue.isNotEmpty()) {
            if (operand1 == null) {
                operand1 = currentValue.toDouble()
            } else {
                val result = calculateIntermediateResult(operand1!!, currentValue.toDouble(), operator)
                if (result != null) {
                    operand1 = result
                }
            }
            operator = op
            amb.titleTextView.text = "Resultado: $operand1 $operator"
            currentValue = ""
        }
    }

    private fun calculateIntermediateResult(operand1: Double, operand2: Double, operator: String?): Double? {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> if (operand2 != 0.0) {
                operand1 / operand2
            } else {
                amb.editTextResult.text.clear()
                null
            }
            else -> null
        }
    }

    private fun calculateResult() {
        val operand2 = currentValue.toDoubleOrNull()
        if (operand1 == null || operand2 == null || operator == null) {
            Toast.makeText(this, "Operação inválida", Toast.LENGTH_SHORT).show()
            return
        }

        val result = calculateIntermediateResult(operand1!!, operand2, operator)

        if (result != null) {
            amb.titleTextView.text = "Resultado: $result"
            currentValue = ""
            operator = null
            operand1 = result
        } else {
            Toast.makeText(this, "Não existe divisão por zero", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clear() {
        amb.editTextResult.text.clear()
        amb.titleTextView.text = "Resultado: "
        currentValue = ""
        operand1 = null
        operator = null
    }
}