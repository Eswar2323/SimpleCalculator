package com.example.simplecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var inputdisplay = findViewById<TextView>(R.id.input)
        var outputdisplay = findViewById<TextView>(R.id.output)

        var button1 = findViewById<Button>(R.id.one)
        var button2 = findViewById<Button>(R.id.two)
        var button3 = findViewById<Button>(R.id.three)
        var button4 = findViewById<Button>(R.id.four)
        var button5 = findViewById<Button>(R.id.five)
        var button6 = findViewById<Button>(R.id.six)
        var button7 = findViewById<Button>(R.id.seven)
        var button8 = findViewById<Button>(R.id.eight)
        var button9 = findViewById<Button>(R.id.nine)
        var button0 = findViewById<Button>(R.id.zero)
        var button00 = findViewById<Button>(R.id.doublezero)

        var buttonequal = findViewById<Button>(R.id.equal)
        var buttondel = findViewById<Button>(R.id.del)
        var buttonac = findViewById<Button>(R.id.ac)

        var buttonadd = findViewById<Button>(R.id.add)
        var buttonsub = findViewById<Button>(R.id.sub)
        var buttonmul = findViewById<Button>(R.id.mul)
        var buttondiv = findViewById<Button>(R.id.div)
        var buttonper = findViewById<Button>(R.id.per)
        var buttondot = findViewById<Button>(R.id.dot)

        var numberbuttons = listOf(button0,button1,button2,button3,button4,button5,button6
                                   ,button7,button8,button9)

        numberbuttons.forEachIndexed { index,button->
            button.setOnClickListener {
                inputdisplay.append(index.toString())
                scrollInputToEnd()
            }
        }

        button00.setOnClickListener(){
            inputdisplay.append("00")
            scrollInputToEnd()
        }

        buttonadd.setOnClickListener(){
            appenedopertor("+")
        }
        buttonsub.setOnClickListener(){
            appenedopertor("-")
        }
        buttonmul.setOnClickListener(){
            appenedopertor("X")
        }
        buttondiv.setOnClickListener(){
            appenedopertor("/")
        }
        buttonper.setOnClickListener(){
            appenedopertor("%")
        }
        buttondot.setOnClickListener(){
            appenedopertor(".")
        }

        buttonac.setOnClickListener() {
            inputdisplay.text=""
            outputdisplay.text=""
        }

        // Code for the Delete Button
        buttondel.setOnClickListener(){
            var input1 = inputdisplay.text.toString()
            if(input1.isNotEmpty()){
                input1 = input1.substring(0,input1.length-1)
                inputdisplay.text = input1
                scrollInputToEnd()
            }
        }

        buttonequal.setOnClickListener {
            evaluateExpression()
        }


    }

    fun appenedopertor(operator: String) {
        val inputdisplay = findViewById<TextView>(R.id.input)
        val text = inputdisplay.text.toString()
        val operators = listOf("+", "-", "X", "/", "%", ".")
        if (text.isEmpty()) {
            return
        }
        val i = text.last().toString()
        if(i !in operators){
            inputdisplay.append(operator)
            scrollInputToEnd()
        }
    }

    fun evaluateExpression() {
        val inputDisplay = findViewById<TextView>(R.id.input)
        val outputDisplay = findViewById<TextView>(R.id.output)

        try {
            var expression = inputDisplay.text.toString()
            expression = expression.replace("X", "*")

            val result = ExpressionBuilder(expression).build().evaluate()

            val longResult = result.toLong()
            if (result == longResult.toDouble()) {
                outputDisplay.text = longResult.toString()
            } else {
                outputDisplay.text = result.toString()
            }

        } catch (e: Exception) {
            outputDisplay.text = "Error"
        }
    }

    fun scrollInputToEnd() {
        val horizontalScrollView = findViewById<HorizontalScrollView>(R.id.horizontalScrollView)
        horizontalScrollView.post {
            horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
            // Or for smooth scrolling:
            // horizontalScrollView.smoothScrollTo(inputdisplay.width, 0)
        }
    }



}