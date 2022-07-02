package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

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
        lastNum = true
        lastDot = false
        input?.append((view as Button).text)
    }

    fun onClear(view : View){
        input?.text = ""
    }

    fun whetherDecimalNeeded(view: View){
        if(lastNum && !lastDot){
            input?.append(".")
            lastNum = false
            lastDot = true
        }
    }
}