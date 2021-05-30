package com.example.cattlesheet

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.cattlesheet.Utils.Companion.bodyWeight
import com.example.cattlesheet.Utils.Companion.fatContentOfMilk
import com.example.cattlesheet.Utils.Companion.pregnantStatus
import com.example.cattlesheet.Utils.Companion.temperate
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var temp : String
    lateinit var bw : String
    lateinit var fc : String
    lateinit var sp : String
    lateinit var milkProduced : String
    var statepreg by Delegates.notNull<Int>()
    var fatContent by Delegates.notNull<Int>()
    var bodyw by Delegates.notNull<Int>()
    var temppos by Delegates.notNull<Int>()
    lateinit var activityBoolean : String
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    companion object{
        var activtyChoosed : Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences  =
        this.getSharedPreferences("MilkProduction", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        milkProduced =
            sharedPreferences.getString("milkProduction", "defaultname").toString()
        statepreg = sharedPreferences.getInt("pregStatus", 0)
        bodyw = sharedPreferences.getInt("bodyWeight", 0)
        temppos = sharedPreferences.getInt("temp", 0)
        fatContent = sharedPreferences.getInt("fatContent", 0)
        activityBoolean = sharedPreferences.getString("activity", "false").toString()
        if (activityBoolean=="false" || activtyChoosed==1) {
            setContentView(R.layout.activity_main)
            activtyChoosed=0
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
                        temp = temperate[position].toString()
                        editor.putInt("temp", position)
                        editor.apply()
                        editor.commit()
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
                        bw = bodyWeight[position].toString()
                        editor.putInt("bodyWeight", position)
                        editor.apply()
                        editor.commit()
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
                        fc = fatContentOfMilk[position].toString()
                        editor.putInt("fatContent", position)
                        editor.apply()
                        editor.commit()
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
                        sp = pregnantStatus[position]
                        editor.putInt("pregStatus", position)
                        editor.apply()
                        editor.commit()
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
            if (milkProduced != "defaultname") {
                dailyMilkProduction.setText(milkProduced).toString()
                spinnerFatContent.setSelection(fatContent)
                spinnerWeight.setSelection(bodyw)
                spinnerPreg.setSelection(statepreg)
                spinnertemp.setSelection(temppos)
            }
            val save = findViewById<Button>(R.id.saveBtn)
            save.setOnClickListener {
                val dailyMilkProduced: String = dailyMilkProduction.text.toString()
                //check if the EditText have values or not
                try {

                    if (dailyMilkProduced.trim()
                            .isNotEmpty() && dailyMilkProduced.toInt() >= 0 && dailyMilkProduced.toInt() <= 20
                    ) {
                        val intent = Intent(this@MainActivity, Calculations::class.java)
                        intent.putExtra("temp", temp)
                        intent.putExtra("bodyWeight", bw)
                        intent.putExtra("fatContent", fc)
                        intent.putExtra("milkProduction", dailyMilkProduction.text.toString())
                        editor.putString("milkProduction", dailyMilkProduction.text.toString())
                        editor.putString("activity", "true")
                        editor.apply()
                        editor.commit()
                        intent.putExtra("stagePregnancy", sp)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Please enter correct value in Daily Milk Production",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } catch (e: Exception) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter correct value in Daily Milk Production",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        else
        {
            val intent = Intent(this@MainActivity, Calculations::class.java)
            intent.putExtra("temp", temperate[temppos].toString())
            intent.putExtra("bodyWeight", bodyWeight[bodyw].toString())
            intent.putExtra("fatContent", fatContentOfMilk[fatContent].toString())
            intent.putExtra("milkProduction", milkProduced.toString())
            intent.putExtra("stagePregnancy", pregnantStatus[statepreg].toString())
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==100)
        {
            editor.putString("activity", "false")
            editor.apply()
            editor.commit()

        }
    }
}
