package com.example.cattlesheet

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import java.util.*


class Utils {
    companion object{
        val  temperate = listOf("ठंडा Cool<27","सामान्य Normal=27","गरम Hot>27")
        val  bodyWeight = listOf(200,250,300,350,400,450,500,550,600,650,700,750,800)
        val fatContentOfMilk= listOf(3,4,5,6,7)
        val pregnantStatus = listOf("गाभिन नहीं NP","6-7","7-8","8-9")
        fun switchLocal(context: Context, lcode: String, activity: Activity) {
            if (lcode.equals("", ignoreCase = true)) return
            val resources: Resources = context.resources
            val locale = Locale(lcode)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            resources.updateConfiguration(config, resources.getDisplayMetrics())
            //restart base activity
            activity.finish()
            activity.startActivity(activity.intent)
        }
    }
}