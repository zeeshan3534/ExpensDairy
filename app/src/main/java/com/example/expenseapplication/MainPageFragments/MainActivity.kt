package com.example.expenseapplication.MainPageFragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.expenseapplication.BudgetFragment
import com.example.expenseapplication.DataStore.PinDataStore
import com.example.expenseapplication.HomeFragment
import com.example.expenseapplication.ProfileFragment
import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.ActivityMainBinding
import com.example.expenseapplication.transactionFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_open_animation) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_close_animation) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.from_bottom_animation) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this,R.anim.to_bottom_animation) }
    private var clicked  = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigationView.background = null
      
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false


        binding.fab.setOnClickListener{
            onAddButtonClick()
        }



        CoroutineScope(Dispatchers.IO).launch {

            val pinManager = PinDataStore(this@MainActivity) // Initialize PinManager with a Context
            val pinFlow = pinManager.pinFlow
            pinFlow.collect { pin ->
                if (pin != null) {
                    Log.d("Actucal pin",pin)
                } else {
                    Log.d("Actual Pin","the pin is empty")
                }
            }
        }




        ///////Bottom navigation


        binding.bottomNavigationView.setOnItemSelectedListener {item->

            Log.d("item navigation",item.itemId.toString())
//            val navController = findNavController(R.id.home_fragment_views) // Replace with your NavHostFragment ID

            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
//                    navController.navigate(R.id.homeFragment)
                }
                R.id.transaction -> {
                    replaceFragment(transactionFragment())
//                    navController.navigate(R.id.transactionFragment2)
                }
                R.id.budget -> {
                    replaceFragment(BudgetFragment())
//                    navController.navigate(R.id.budgetFragment)
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
//                    navController.navigate(R.id.profileFragment)
                }
            }

            true

        }



        ////////////////
    }


    ///////Fab button Animation functionality
    private fun onAddButtonClick() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked:Boolean) {
        if (!clicked){
            binding.incoming.visibility = View.VISIBLE
            binding.outgoingConstraint.visibility = View.VISIBLE
            binding.addCategory.visibility = View.VISIBLE
        }else{
            binding.incoming.visibility = View.INVISIBLE
            binding.outgoingConstraint.visibility = View.INVISIBLE
            binding.addCategory.visibility = View.INVISIBLE
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            binding.incoming.startAnimation(fromBottom)
            binding.outgoingConstraint.startAnimation(fromBottom)
            binding.addCategory.startAnimation(fromBottom)
            binding.fab.startAnimation(rotateOpen)
        }else{
            binding.incoming.startAnimation(toBottom)
            binding.addCategory.startAnimation(toBottom)
            binding.outgoingConstraint.startAnimation(toBottom)
            binding.fab.startAnimation(rotateClose)
        }
    }




    //////////////////////////
    private fun replaceFragment(fragment : Fragment){
         val fragmentManager = supportFragmentManager
         val fragmentTransaction = fragmentManager.beginTransaction()
         fragmentTransaction.replace(R.id.fragmentContainerView2,fragment)
         fragmentTransaction.commit()
     }
    
    
}