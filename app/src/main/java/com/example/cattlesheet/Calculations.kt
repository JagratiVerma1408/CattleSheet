package com.example.cattlesheet

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TableLayout
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
    lateinit var tablelayout:TableLayout
    lateinit var milkProductionB:TextView
    lateinit var pregnancyC :TextView
    lateinit var m1:TextView
    lateinit var m2:TextView
    lateinit var p1:TextView
    lateinit var p2:TextView
    lateinit var tr1:TextView
    lateinit var tr2:TextView
    lateinit var mp1:TextView
    lateinit var mp2:TextView
    lateinit var LGF1:TextView
    lateinit var LGF3:TextView
    lateinit var NLGF2:TextView
    lateinit var NLGF3:TextView
    lateinit var DF1:TextView
    lateinit var DF2:TextView
    lateinit var DF3:TextView
    lateinit var C3:TextView
    lateinit var C1:TextView
    lateinit var C2:TextView

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
        m1=findViewById(R.id.m1)
        m2=findViewById(R.id.m2)
        p1=findViewById(R.id.p1)
        p2=findViewById(R.id.P2)
        tr1=findViewById(R.id.tr1)
        tr2=findViewById(R.id.tr2)
        mp1=findViewById(R.id.mp1)
        mp2=findViewById(R.id.mp2)
        NLGF2=findViewById(R.id.NLGF2)
        NLGF3=findViewById(R.id.NLGF3)
        LGF1=findViewById(R.id.LGF1)
        LGF3=findViewById(R.id.LGF3)
        DF1=findViewById(R.id.DR1)
        DF2=findViewById(R.id.DR2)
        DF3=findViewById(R.id.DR3)
        C1=findViewById(R.id.C1)
        C2=findViewById(R.id.C2)
        C3=findViewById(R.id.C3)

        milkProductionB=findViewById(R.id.dryMattReqMilkPro)
        pregnancyC=findViewById(R.id.dryMattReqPreg)
        totalDryMatter=findViewById(R.id.totalReqDry)
        tablelayout=findViewById(R.id.tablelayout)
        dmiMax=findViewById(R.id.maxDMI)
        actualDryMatter=findViewById(R.id.actualDryMatter)
        dailyWaterReq=findViewById(R.id.dailyWaterReq)
        AMaintenance()
        BMilkProduction()
        CPregnanacy()
        tr1.text=(m1.text.toString().toFloat() +mp1.text.toString().toFloat()+p1.text.toString().toFloat()).toString()
        tr2.text=(m2.text.toString().toFloat() +mp2.text.toString().toFloat()+p2.text.toString().toFloat()).toString()
        total=a+b+c
        dailyWaterRequirement()
        totalDryMatter.text=  df.format(total).toString()
        dmiMax.text=  df.format(dmax).toString()
        actualDryMatter.text= df.format(min(total,dmax)).toString()
        table2()
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
        when (bodyWeight.toInt()) {
            200 -> m1.text= 6.94F.toString()
            250 -> m1.text= 8.24F.toString()
            300 -> m1.text= 9.47F.toString()
            350 -> m1.text= 10.67F.toString()
            400 -> m1.text= 11.82F.toString()
            450 -> m1.text= 12.94F.toString()
            500 -> m1.text= 14.04F.toString()
            550 -> m1.text= 15.1F.toString()
            600 -> m1.text= 16.15F.toString()
            650 -> m1.text= 17.18F.toString()
            700 -> m1.text= 18.19F.toString()
            750 -> m1.text= 19.19F.toString()
            800 -> m1.text= 20.17F.toString()
            else -> {m1.text= 6.94F.toString()}
        }
        when (bodyWeight.toInt()) {
            200 -> m2.text= 259.toString()
            250 -> m2.text= 306.toString()
            300 -> m2.text= 351.toString()
            350 -> m2.text= 394.toString()
            400 -> m2.text= 436.toString()
            450 -> m2.text= 476.toString()
            500 -> m2.text= 515.toString()
            550 -> m2.text= 553.toString()
            600 -> m2.text= 591.toString()
            650 -> m2.text= 627.toString()
            700 -> m2.text= 663.toString()
            750 -> m2.text= 698.toString()
            800 -> m2.text= 733.toString()
            else -> {m2.text= 259.toString()}
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
        when (fatContent.toInt()) {
            1-> mp1.text= (1.05*damp).toString()
            2-> mp1.text= (1.2*damp).toString()
            3-> mp1.text= (1.34*damp).toString()
            4-> mp1.text= (1.5*damp).toString()
            5-> mp1.text= (1.64*damp).toString()
        }
        mp2.text= (96*damp).toString()
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
        when (pregStatus) {
            pregnantStatus[0]-> p1.text = "0"
            pregnantStatus[1]-> p1.text = "2.3"
            pregnantStatus[2]-> p1.text = "2.67"
            pregnantStatus[3]-> p1.text = "3.05"
        }
        when (pregStatus) {
            pregnantStatus[0]-> p2.text = "0"
            pregnantStatus[1]-> p2.text = "169"
            pregnantStatus[2]-> p2.text = "216"
            pregnantStatus[3]-> p2.text = "263"
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
    fun table2()
    { var x:Int=0
        when (milkProduction.toInt()) {
            0 -> x=29
            1 -> x=33
            2 -> x=37
            3 -> x=40
            4-> x=44
            5-> x=46
            6-> x=35
            7->x=36
            8->x=37
            9->x=38
            10->x=39
            11->x=38
            12->x=39
            13->x=40
            14->x=43
            15->x=46
            16->x=42
            17->x=43
            18->x=47
            19->x=48
            20->x=50
            else -> {x=29}
        }
        LGF1.text=(actualDryMatter.text.toString().toFloat()*x*0.05).toString()

    }
}