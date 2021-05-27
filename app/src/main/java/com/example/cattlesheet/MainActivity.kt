package com.example.cattlesheet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.cattlesheet.Utils.Companion.bodyWeight
import com.example.cattlesheet.Utils.Companion.fatContentOfMilk
import com.example.cattlesheet.Utils.Companion.pregnantStatus
import com.example.cattlesheet.Utils.Companion.temperate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinnertemp = findViewById<Spinner>(R.id.temprature)
        if (spinnertemp != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, temperate
            )
            spinnertemp.adapter = adapter

            spinnertemp.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        temperate[position], Toast.LENGTH_SHORT
//                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Select a value", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        val spinnerWeight = findViewById<Spinner>(R.id.bodyWeight)
        if (spinnerWeight != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, bodyWeight
            )
            spinnerWeight.adapter = adapter

            spinnerWeight.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        bodyWeight[position].toString(), Toast.LENGTH_SHORT
//                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Select a value", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        val spinnerFatContent = findViewById<Spinner>(R.id.fatContent)
        if (spinnerFatContent != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, fatContentOfMilk
            )
            spinnerFatContent.adapter = adapter

            spinnerFatContent.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        fatContentOfMilk[position].toString(), Toast.LENGTH_SHORT
//                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Select a value", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        val spinnerPreg = findViewById<Spinner>(R.id.pregState)
        if (spinnerPreg != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, pregnantStatus
            )
            spinnerPreg.adapter = adapter

            spinnerPreg.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
//                    Toast.makeText(
//                        this@MainActivity,
//                        pregnantStatus[position], Toast.LENGTH_SHORT
//                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please Select a value", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        val dailyMilkProduction = findViewById<EditText>(R.id.milkProduction)
        val save = findViewById<Button>(R.id.saveBtn)
        save.setOnClickListener {
            val dailyMilkProduced: String = dailyMilkProduction.text.toString()
            //check if the EditText have values or not
            if (dailyMilkProduced.trim().isNotEmpty()) {
                val intent = Intent(this@MainActivity, Calculations::class.java)
                intent.putExtra("product_name", dailyMilkProduction.text.toString())
                intent.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Please enter some value in Daily Milk Production",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
