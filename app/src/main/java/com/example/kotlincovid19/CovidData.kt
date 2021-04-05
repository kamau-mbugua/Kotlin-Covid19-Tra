package com.example.kotlincovid19

import com.google.gson.annotations.SerializedName

data class CovidData {
   val dataChecked:String,
   val positiveChecked:Int,
   val negativeChecked:Int,
   val deathChecked:Int,
   val state:String
}