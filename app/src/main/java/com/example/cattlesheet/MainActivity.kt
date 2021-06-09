 package com.example.cattlesheet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.cattlesheet.Utils.Companion.bodyWeight
import com.example.cattlesheet.Utils.Companion.fatContentOfMilk
import com.example.cattlesheet.Utils.Companion.pregnantStatus
import com.example.cattlesheet.Utils.Companion.temperate
import kotlin.properties.Delegates

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var temp : String
    lateinit var bw : String
    lateinit var fc : String
    lateinit var sp : String
    lateinit var lang : String
    lateinit var tempSelect: TextView
    lateinit var selectmilkProd : TextView
    lateinit var selectBodyWeight : TextView
    lateinit var selectfatcontent : TextView
    lateinit var selectstatpreg : TextView
    lateinit var milkProduced : String
    lateinit var save : Button
    lateinit var dailyMilkProduction : EditText
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
            save = findViewById(R.id.saveBtn)
            textViews()
            val intent = intent
            lang = intent.getStringExtra("language").toString()
            //Toast.makeText(this,lang,Toast.LENGTH_SHORT).show()
            if(lang=="hindi")
            {
                setToHindiLng()
            }
            else
            {
                setToEngLng()
            }
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
                        intent.putExtra("language", lang)
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
            lang = intent.getStringExtra("language").toString()
            val intent = Intent(this@MainActivity, Calculations::class.java)
            intent.putExtra("temp", temperate[temppos].toString())
            intent.putExtra("bodyWeight", bodyWeight[bodyw].toString())
            intent.putExtra("fatContent", fatContentOfMilk[fatContent].toString())
            intent.putExtra("milkProduction", milkProduced.toString())
            intent.putExtra("stagePregnancy", pregnantStatus[statepreg].toString())
            intent.putExtra("language", lang)
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

    fun setToHindiLng()
    {
        save.text=getString(R.string.nextHindi)
        dailyMilkProduction.hint=getString(R.string.rangehintHindi)
        selectstatpreg.text= getString(R.string.pregHindi)
        selectmilkProd.text=getString(R.string.daily_milk_enter)
        tempSelect.text=getString(R.string.temp_Select)
        selectfatcontent.text=getString(R.string.fat_content)
        selectBodyWeight.text=getString(R.string.body_weight)
    }
    fun setToEngLng()
    {
        save.text=getString(R.string.next)
        dailyMilkProduction.hint=getString(R.string.range_from_0_to_20_only_integers)
        selectstatpreg.text= getString(R.string.select_the_stage_of_pregnancy_from_the_drop_down_list)
        selectmilkProd.text=getString(R.string.enter_daily_milk_production_lit_h_d)
        tempSelect.text=getString(R.string.select_the_atmospheric_temperature_c_from_the_drop_down_list)
        selectfatcontent.text=getString(R.string.select_the_fat_content_of_milk_from_the_drop_down_list)
        selectBodyWeight.text=getString(R.string.select_the_body_weight_of_the_animal_from_the_drop_down_list)
    }
    fun textViews()
    {
        dailyMilkProduction = findViewById(R.id.milkProduction)
        selectBodyWeight=findViewById(R.id.select_body_Weight)
        selectfatcontent=findViewById(R.id.fat_content_select)
        tempSelect=findViewById(R.id.select_temp)
        selectmilkProd=findViewById(R.id.select_milk_production)
        selectstatpreg=findViewById(R.id.select_pregnancy)
    }
}
