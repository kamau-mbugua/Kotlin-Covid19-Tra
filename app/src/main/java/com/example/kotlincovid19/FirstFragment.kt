package com.example.kotlincovid19

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var perStateDailyData: Map<String, List<CovidData>>
    private lateinit var nationalDailyData: List<CovidData>
    private val BASE_URL = "https://covodtracking.com/api/v1/"
    private  val TAG = "FirstFragment"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

         val  covidService = retrofit.create(CovidService::class.java)

        //Fetch national data
        covidService.getNationalData().enqueue(object : Callback<List<CovidData>>
        {
            override fun onResponse(call: Call<List<CovidData>>, response: Response<List<CovidData>>) {
                TODO("Not yet implemented")
                Log.i(TAG, "onResponse $response")
                val nationalData = response.body()
                if (nationalData ==  null){
                    Log.w(TAG, "Did not Recieve data responce")
                    return
                }
                nationalDailyData= nationalData.reversed()
                Log.i(TAG, "Update graph")

            }

            override fun onFailure(call: Call<List<CovidData>>, t: Throwable) {
                TODO("Not yet implemented")
                Log.e(TAG, "onFilure $t")
            }

        })
        //Fetch State data

        covidService.getStatesData().enqueue(object : Callback<List<CovidData>>
        {
            override fun onResponse(call: Call<List<CovidData>>, response: Response<List<CovidData>>) {
                TODO("Not yet implemented")
                Log.i(TAG, "onResponse $response")
                val statesData = response.body()
                if (statesData ==  null){
                    Log.w(TAG, "Did not Recieve data responce")
                    return
                }
                perStateDailyData= statesData.reversed().groupBy { it.state }
                Log.i(TAG, "Update spinner")

            }

            override fun onFailure(call: Call<List<CovidData>>, t: Throwable) {
                TODO("Not yet implemented")
                Log.e(TAG, "onFilure $t")
            }

        })
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}