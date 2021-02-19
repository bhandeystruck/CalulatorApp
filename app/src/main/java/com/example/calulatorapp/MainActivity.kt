package com.example.calulatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    //Helper variables to deal with one decimal point per number or equation
    //this variable will contain info if its containing numeric value for the last button that was pressed
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    //onclick function when we press the buttons in tv
    fun onDigit(view: View){
        //need to get the texdt from the button and display it
        //get the text from a button and cast it to Button
        tvInput.append((view as Button).text)
        lastNumeric = true

    }

    //we need to get a view when this function is a called hence passing it
    fun onClear(view: View){
        //we just clear the textview Input with an empty sting
        tvInput.text = ""

        //se we need to set all the variables to default here cause we clear
        lastNumeric = false
        lastDot = false

    }

    //this funtion will check if a dot was used earlier if so it wont execute
    //if not dot was used then it will append a dot
    fun onDecimalPoint (view: View){
        //run something if the value before was a number and does not have a dot before it
        if (lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric = false
            //because now we pressed the dot
            lastDot = true

        }
    }

    fun onEqual (view: View){
        //first we need to check if the value is numeric or not
        //so only when the last number is a number we can do =
        if (lastNumeric){
            var tvValue = tvInput.text.toString()

            var prefix = ""

            try{

                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                    //-215 this line for now will disregard the -
                    //and use tv value as 215
                }

                if(tvValue.contains("-")){
                        //we need to slip up the text
                        //99-1
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0] //99
                        var two = splitValue[1] //1

                        //making it a negative value
                        if (!prefix.isEmpty()){
                            one = prefix + one
                        }

                        tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                else if (tvValue.contains("*")){
                    //we need to slip up the text
                    //99-1
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    //making it a negative value
                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
                else if (tvValue.contains("+")){
                    //we need to slip up the text
                    //99-1
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    //making it a negative value
                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
                else if (tvValue.contains("/")){
                    //we need to slip up the text
                    //99-1
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    //making it a negative value
                    if (!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }


            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    //function for operators if no operator is used before we can use it
    //if operator is used before we dont use it
    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    //function to check if the operator is clicked and to check the - condition
  private fun isOperatorAdded(value:String) : Boolean{
      return if(value.startsWith("-")){
          false
      }
      else{
          value.contains("/") || value.contains("*")
                  || value.contains("+") || value.contains("-")
      }
  }


    private fun removeZeroAfterDot(result : String) : String {
        var value = result
        if (result.contains(".0"))
        {
            //use the string as it is but remove last two characters
            value = result.substring(0, result.length-2)
            //99,0
            //so this code will chop off .0

        }
        return value
    }



}