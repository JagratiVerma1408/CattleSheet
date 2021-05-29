package com.example.cattlesheet

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.example.cattlesheet.Utils.Companion.pregnantStatus
import com.example.cattlesheet.Utils.Companion.temperate
import org.w3c.dom.Text
import java.text.DecimalFormat
import kotlin.math.min


class Calculations : AppCompatActivity() {
    lateinit var temp:String
    lateinit var fatContent:String
    lateinit var milkProduction :String
    lateinit var pregStatus :String
    lateinit var bodyWeight :String
    lateinit var maintananceA:TextView
    lateinit var milkProductionB:TextView
    lateinit var pregnancyC :TextView
    lateinit var totalDryMatter : TextView
    lateinit var dmiMax : TextView
    lateinit var actualDryMatter : TextView
    lateinit var dailyWaterReq : TextView
    var a:Float = 0.00f
    var b:Float = 0.00f
    var c:Float = 0.00f
    var total:Float = 0.00f
    var dmax:Float = 0.0f
    val df= DecimalFormat("#.##")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculations)
        temp = intent.getStringExtra("temp").toString()
        fatContent = intent.getStringExtra("fatContent").toString()
        milkProduction = intent.getStringExtra("milkProduction").toString()
         pregStatus  = intent.getStringExtra("stagePregnancy").toString()
        bodyWeight  = intent.getStringExtra("bodyWeight").toString()
        Toast.makeText(this,temp+fatContent+bodyWeight+pregStatus+milkProduction,Toast.LENGTH_SHORT).show()
        maintananceA=findViewById(R.id.dryMattReqMaintan)
        milkProductionB=findViewById(R.id.dryMattReqMilkPro)
        pregnancyC=findViewById(R.id.dryMattReqPreg)
        totalDryMatter=findViewById(R.id.totalReqDry)
        dmiMax=findViewById(R.id.maxDMI)
        actualDryMatter=findViewById(R.id.actualDryMatter)
        dailyWaterReq=findViewById(R.id.dailyWaterReq)
        AMaintenance()
        BMilkProduction()
        CPregnanacy()
        total=a+b+c
        dailyWaterRequirement()
        totalDryMatter.text=  df.format(total).toString()
        dmiMax.text=  df.format(dmax).toString()
        actualDryMatter.text= df.format(min(total,dmax)).toString()
    }

    @SuppressLint("SetTextI18n")
    fun AMaintenance()
    {

        when (bodyWeight.toInt()) {
            200 -> a= 4.31f
            250 -> a=5.40F
            300 ->a= 6.48F
            350 ->a=7.56F
            400 ->a=8.64F
            450 ->a=9.71f
            500 ->a=10.80F
            550 ->a=11.88F
            600 ->a=12.96F
            650 ->a=14.04F
            700 ->a=15.11f
            750 ->a=16.20F
            800 ->a=17.28F
        }
        maintananceA.text= df.format(a).toString()
        when (bodyWeight.toInt()) {
            200 -> dmax= 8.00F
            250 -> dmax=10.00F
            300 ->dmax=12.00F
            350 ->dmax=14.00F
            400 ->dmax=16.00F
            450 ->dmax=18.00F
            500 ->dmax=20.00F
            550 ->dmax=22.00F
            600 ->dmax=24.00F
            650 ->dmax=26.00F
            700 ->dmax=28.00F
            750 ->dmax=30.00F
            800 ->dmax=32.00F
        }

    }
    fun BMilkProduction()
    {
        val damp = milkProduction.toFloat()
        when (fatContent.toInt()) {
            1-> b= (0.45*damp).toFloat()
            2-> b= (0.51*damp).toFloat()
            3-> b= (0.57*damp).toFloat()
            4-> b = (0.64*damp).toFloat()
            5-> b = (0.70*damp).toFloat()
        }
       milkProductionB.text= df.format(b).toString()
    }

    fun CPregnanacy()
    {
        when (pregStatus) {
            pregnantStatus[0]-> pregnancyC.text = "0.00"
            pregnantStatus[1]-> pregnancyC.text = "0.85"
            pregnantStatus[2]-> pregnancyC.text = "0.99"
            pregnantStatus[3]-> pregnancyC.text = "1.13"
        }
        when (pregStatus) {
            pregnantStatus[0]-> c = 0.00F
            pregnantStatus[1]-> c = 0.85F
            pregnantStatus[2]-> c = 0.99F
            pregnantStatus[3]-> c = 1.13F
        }

    }

    fun dailyWaterRequirement()
    {
        when(temp)
        {
            temperate[0]->dailyWaterReq.text= df.format(min(total,dmax)*3).toString()
            temperate[1]->dailyWaterReq.text=  df.format(min(total,dmax)*3.5).toString()
            temperate[2]->dailyWaterReq.text= df.format(min(total,dmax)*4.5).toString()
        }
    }
}