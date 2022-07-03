package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var input: TextView? = null

    private var lastNum : Boolean = false

    private var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
    }

    fun onDigit(view : View){
        input?.append((view as Button).text)
        lastNum = true
        lastDot = false
    }

    fun onClear(view: View) {
        input?.text = ""
        lastNum = false
        lastDot = false
    }

    fun whetherDecimalNeeded(view: View) {
        if(lastNum && !lastDot){
            input?.append(".")
            lastNum = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        input?.text?.let {
            if (lastNum && !isOperatorAdded(it.toString())){
                input?.append((view as Button).text)
                lastNum = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")){
            false
        } else {
            value.contains("/") || value.contains("+") || value.contains("*")|| value.contains("-")
        }
    }

    fun equal(view: View) {

        if(lastNum) {

            var theValue = input?.text.toString()
            var prefix = "-"

            try {
                if (theValue.startsWith("-")) {
                    prefix = "-"
                    theValue = theValue.substring(1)
                }

                when {
                    theValue.contains("/") -> {
                        // Will split the inputValue using Division operator
                        val splitedValue = theValue.split("/")

                        var one = splitedValue[0] // Value One
                        val two = splitedValue[1] // Value Two

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                            one = prefix + one
                        }

                        /*Here as the value one and two will be calculated based on the operator and
                                if the result contains the zero after decimal point will remove it.
                                And display the result to TextView*/
                        input?.text =
                            removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                    theValue.contains("*") -> {
                        // If the inputValue contains the Multiplication operator
                        // Will split the inputValue using Multiplication operator
                        val splitedValue = theValue.split("*")

                        var one = splitedValue[0] // Value One
                        val two = splitedValue[1] // Value Two

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                            one = prefix + one
                        }

                        input?.text =
                            removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }
                    theValue.contains("-") -> {

                        // If the inputValue contains the Subtraction operator
                        // Will split the inputValue using Subtraction operator
                        val splitedValue = theValue.split("-")

                        var one = splitedValue[0] // Value One
                        val two = splitedValue[1] // Value Two

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                            one = prefix + one
                        }

                        input?.text =
                            removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }
                    theValue.contains("+") -> {
                        // If the inputValue contains the Addition operator
                        // Will split the inputValue using Addition operator
                        val splitedValue = theValue.split("+")

                        var one = splitedValue[0] // Value One
                        val two = splitedValue[1] // Value Two

                        if (prefix.isNotEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                            one = prefix + one
                        }

                        input?.text =
                            removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }
                }
            }
            catch(e: ArithmeticException) {
                e.printStackTrace()
            }
        }

    }

        private fun removeZeroAfterDot(res : String) : String{
        var value = res
        if(res.contains(".0")){
            value = res.substring(0, res.length-2)
        }
        return value
    }

}
