package com.example.expenseapplication.MainPageFragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expenseapplication.Adaptors.homeCategoryAdaptor
import com.example.expenseapplication.Models.Categories
import com.example.expenseapplication.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        Log.d("current month", sdf.format(currentDate).toString())
        binding.currentMonth.setText(sdf.format(currentDate).toString())

////////////////Line Graph functionality
        val lineChart =  binding.lineChart
        lineChart.gradientFillColors = intArrayOf(
            Color.parseColor("#EEE5FF")
            , Color.TRANSPARENT

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





        ///////////////Firebase Realtime database

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val databaseReference = FirebaseDatabase.getInstance().getReference("users/$uid")


        databaseReference.child("Categoryname").addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = mutableListOf<Categories>()

                for(categorySnapShot in snapshot.children){
                    val categoryname = categorySnapShot.key.toString()
                    val icon = categorySnapShot.child("icon").value.toString()
                    val category = Categories(categoryname,icon)
                    categoryList.add(category)
                }


                ///Adding data to recycler view
                val recyclerView = binding.showrecentTransaction
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = homeCategoryAdaptor(categoryList)
                //////
                Log.d("all the category",categoryList.toString())
            }


            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Getting Data from firebase is unavilable",Toast.LENGTH_LONG).show()
            }

        })




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