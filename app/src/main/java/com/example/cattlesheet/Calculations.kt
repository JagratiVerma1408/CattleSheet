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
    var a:Float = 0.000f
    var b:Float = 0.000f
    var c:Float = 0.000f
    var total:Float = 0.000f
    var dmax:Float = 0.000f
    val df= DecimalFormat("#.##")
    val df1= DecimalFormat("#.###")
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
        tr1.text=df.format(m1.text.toString().toFloat() +mp1.text.toString().toFloat()+p1.text.toString().toFloat()).toString()
        tr2.text=(m2.text.toString().toInt() +mp2.text.toString().toInt()+p2.text.toString().toInt()).toString()
        total=a+b+c
        dailyWaterRequirement()
        totalDryMatter.text=  df1.format(total).toString()
        dmiMax.text=  df1.format(dmax).toString()
        actualDryMatter.text= df1.format(min(total,dmax)).toString()
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
        maintananceA.text= df1.format(a).toString()
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
            3-> b= (0.45*damp).toFloat()
            4-> b= (0.51*damp).toFloat()
            5-> b= (0.57*damp).toFloat()
            6-> b = (0.64*damp).toFloat()
            7-> b = (0.70*damp).toFloat()
        }
       milkProductionB.text= df1.format(b).toString()
        when (fatContent.toInt()) {
            3-> mp1.text= (1.05*damp).toString()
            4-> mp1.text= (1.2*damp).toString()
            5-> mp1.text= (1.34*damp).toString()
            6-> mp1.text= (1.5*damp).toString()
            7-> mp1.text= (1.64*damp).toString()
        }
        mp2.text= (96*damp).toInt().toString()
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
            temperate[0]->dailyWaterReq.text= df1.format(min(total,dmax)*3).toString()
            temperate[1]->dailyWaterReq.text=  df1.format(min(total,dmax)*3.5).toString()
            temperate[2]->dailyWaterReq.text= df1.format(min(total,dmax)*4.5).toString()
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
        LGF1.text=df.format(actualDryMatter.text.toString().toFloat()*x*0.05).toString()
        when (milkProduction.toInt()) {
            0 -> x=10
            1 -> x=13
            2 -> x=17
            3 -> x=15
            4-> x=15
            5-> x=15
            6-> x=10
            7->x=10
            8->x=15
            9->x=15
            10->x=15
            11->x=15
            12->x=15
            13->x=15
            14->x=15
            15->x=15
            16->x=15
            17->x=15
            18->x=15
            19->x=20
            20->x=20
            else -> {x=10}
        }
        LGF3.text=df.format(actualDryMatter.text.toString().toFloat()*x*0.05).toString()
        when (milkProduction.toInt()) {
            0 -> x=50
            1 -> x=50
            2 -> x=50
            3 -> x=50
            4-> x=50
            5-> x=50
            6-> x=50
            7->x=55
            8->x=60
            9->x=65
            10->x=70
            11->x=55
            12->x=55
            13->x=55
            14->x=55
            15->x=55
            16->x=50
            17->x=55
            18->x=55
            19->x=51
            20->x=46
            else -> {x=50}
        }
        NLGF2.text=df.format(actualDryMatter.text.toString().toFloat()*x*0.0333).toString()

        when (milkProduction.toInt()) {
            0 -> x=40
            1 -> x=40
            2 -> x=40
            3 -> x=40
            4-> x=40
            5-> x=40
            6-> x=40
            7->x=45
            8->x=40
            9->x=45
            10->x=50
            11->x=50
            12->x=55
            13->x=45
            14->x=40
            15->x=40
            16->x=35
            17->x=35
            18->x=35
            19->x=31
            20->x=40
            else -> {x=40}
        }
        NLGF3.text=df.format(actualDryMatter.text.toString().toFloat()*x*0.0333).toString()
        when (milkProduction.toInt()) {
            0 -> x=71
            1 -> x=67
            2 -> x=63
            3 -> x=60
            4-> x=56
            5-> x=54
            6-> x=55
            7->x=53
            8->x=51
            9->x=49
            10->x=47
            11->x=47
            12->x=45
            13->x=43
            14->x=39
            15->x=35
            16->x=38
            17->x=32
            18->x=23
            19->x=17
            20->x=10
            else -> {x=71}
        }
        DF1.text=df.format(actualDryMatter.text.toString().toFloat()*x*0.0111).toString()
        when (milkProduction.toInt()) {
            0 -> x=40
            1 -> x=37
            2 -> x=34
            3 -> x=31
            4-> x=28
            5-> x=25
            6-> x=25
            7->x=20
            8->x=15
            9->x=10
            10->x=5
            11->x=14
            12->x=13
            13->x=11
            14->x=8
            15->x=5
            16->x=10
            17->x=3
            18->x=0
            19->x=0
            20->x=0
            else -> {x=40}
        }
        DF2.text=df.format(actualDryMatter.text.toString().toFloat()*x*0.0111).toString()
        when (milkProduction.toInt()) {
            0 -> x=45
            1 -> x=42
            2 -> x=38
            3 -> x=35
            4-> x=31
            5-> x=29
            6-> x=30
            7->x=25
            8->x=25
            9->x=20
            10->x=15
            11->x=15
            12->x=10
            13->x=15
            14->x=15
            15->x=10
            16->x=15
            17->x=15
            18->x=10
            19->x=5
            20->x=0
            else -> {x=71}
        }
        DF3.text=df.format(actualDryMatter.text.toString().toFloat()*x*0.0111).toString()
        when (milkProduction.toInt()) {
            0 -> x=0
            1 -> x=0
            2 -> x=0
            3 -> x=0
            4-> x=0
            5-> x=0
            6-> x=10
            7->x=11
            8->x=12
            9->x=13
            10->x=14
            11->x=15
            12->x=16
            13->x=17
            14->x=18
            15->x=19
            16->x=20
            17->x=25
            18->x=30
            19->x=35
            20->x=40
            else -> {x=0}
        }
        C1.text=df.format(actualDryMatter.text.toString().toFloat()*x*0.0111).toString()
        when (milkProduction.toInt()) {
            0 -> x=10
            1 -> x=13
            2 -> x=16
            3 -> x=19
            4-> x=22
            5-> x=25
            6-> x=25
            7->x=25
            8->x=25
            9->x=25
            10->x=25
            11->x=31
            12->x=32
            13->x=34
            14->x=37
            15->x=40
            16->x=40
            17->x=42
            18->x=45
            19->x=49
            20->x=54
            else -> {x=10}
        }
        C2.text=df.format(actualDryMatter.text.toString().toFloat()*x*0.0111).toString()
        when (milkProduction.toInt()) {
            0 -> x=5
            1 -> x=5
            2 -> x=5
            3 -> x=10
            4-> x=14
            5-> x=16
            6-> x=20
            7->x=20
            8->x=20
            9->x=20
            10->x=20
            11->x=20
            12->x=20
            13->x=25
            14->x=30
            15->x=35
            16->x=35
            17->x=35
            18->x=40
            19->x=40
            20->x=40
            else -> {x=5}
        }
        C3.text=df.format(actualDryMatter.text.toString().toFloat()*x*0.0111).toString()


    }
}