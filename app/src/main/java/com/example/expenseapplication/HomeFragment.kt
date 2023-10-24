package com.example.expenseapplication

import android.graphics.Color
import android.os.Bundle
import android.os.DropBoxManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.expenseapplication.databinding.FragmentHomeBinding


import java.text.SimpleDateFormat
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Date
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    var j:Float = 0.0F
    private lateinit var binding  : FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)


        val currentDate = Date()
        val sdf = SimpleDateFormat("MMMM", Locale.getDefault())
        Log.d("current month",sdf.format(currentDate).toString())
        binding.currentMonth.setText(sdf.format(currentDate).toString())

////////////////Line Graph functionality
        val lineChart =  binding.lineChart
        lineChart.gradientFillColors = intArrayOf(
            Color.parseColor("#EEE5FF")
            ,Color.TRANSPARENT

        )
        lineChart.animation.duration= animationDuration
        lineChart.animate(lineSet)
        lineChart.onDataPointClickListener ={index, _, _ ->
            binding.chartData.text =
                lineSet.toList()[index]
                    .second
                    .toString()
        }

//////////////////////////////////





        /////////////token
//        Firebase.messaging.token.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val token = task.result
//              Log.d(
//                  "Device token ",
//                  token.toString()
//              )
//            } else {
//                // Handle the error
//                val exception = task.exception
//                // Handle the exception
//            }
//        }


        return binding.root
    }


//    //////Graph work
    companion object{
        private val lineSet = listOf(
            "label1" to 4F,
            "label2" to 6F,
            "label3" to 5F,
            "label4" to 4F,
            "label5" to 4.5F,

        )
        private const val animationDuration = 1000L
    }

    //////



}