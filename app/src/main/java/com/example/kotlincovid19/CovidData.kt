package com.example.kotlincovid19

import com.google.gson.annotations.SerializedName
import java.util.*

data class CovidData (
   @SerializedName("dateChecked") val dataChecked:String,
   @SerializedName("positiveIncrease") val positiveChecked:Int,
   @SerializedName("negativeIncrease") val negativeChecked:Int,
   @SerializedName("deathIncrease") val deathIncrease:Int,
   @SerializedName("state") val state:String
)