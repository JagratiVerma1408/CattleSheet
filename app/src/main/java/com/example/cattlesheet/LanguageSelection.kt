package com.example.cattlesheet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.cardview.widget.CardView

class LanguageSelection : AppCompatActivity() {
    lateinit var english : RadioButton
    lateinit var hindi : RadioButton
    lateinit var radioGrp: RadioGroup
    lateinit var language : String
    lateinit var lang : String
    lateinit var next : CardView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    companion object{
        var activtylang : Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences  =
            this.getSharedPreferences("Language", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        lang =
            sharedPreferences.getString("language", "").toString()
        if (lang.isEmpty() || activtylang==1) {
            setContentView(R.layout.activity_language_selection)
            english = findViewById(R.id.en)
            hindi = findViewById(R.id.hin)
            radioGrp = findViewById(R.id.radioGrp)
            next = findViewById(R.id.next)
            language = ""
            if(activtylang==1)
            {
                if(lang=="english")
                {
                    english.isChecked=true
                }
                else
                {
                    hindi.isChecked=true
                }
                activtylang=0
            }

            next.setOnClickListener {
                if (english.isChecked) {
                    language = "english"
                    hindi.isChecked = false
                }
                if (hindi.isChecked) {
                    language = "hindi"
                    english.isChecked = false
                }
                if (language.isNotEmpty()) {

                    val intent = Intent(this@LanguageSelection, MainActivity::class.java)
                    intent.putExtra("language", language)
                    editor.putString("language", language)
                    editor.apply()
                    editor.commit()
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "Please Select any One Language", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        else
        {
            val intent = Intent(this@LanguageSelection, MainActivity::class.java)
            intent.putExtra("language", lang)
            startActivity(intent)
            finish()
        }


    }
}