package com.example.expenseapplication.MainPageFragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.expenseapplication.MainPageFragments.BudgetFragment
import com.example.expenseapplication.DataStore.PinDataStore
import com.example.expenseapplication.MainPageFragments.HomeFragment
import com.example.expenseapplication.MainPageFragments.ProfileFragment
import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.ActivityMainBinding
import com.example.expenseapplication.MainPageFragments.transactionFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow


class MainActivity : AppCompatActivity() {

    private lateinit var dialog:BottomSheetDialog
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


///////Add category
        binding.addCategory.setOnClickListener {
            openBottomSheet()
        }
            ///////////

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


    ///////Bottom sheet
    private fun openBottomSheet() {
        val dialogBoxView = layoutInflater.inflate(R.layout.category_bottom_sheet,null)

        var tag = ""
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val categoryName = dialogBoxView.findViewById<EditText>(R.id.category_name)
        val selectedImage = dialogBoxView.findViewById<ImageView>(R.id.selectedImage)
        val submitCategory = dialogBoxView.findViewById<RelativeLayout>(R.id.add_data)
        val tax  = dialogBoxView.findViewById<RelativeLayout>(R.id.tax)
        val receipt  = dialogBoxView.findViewById<RelativeLayout>(R.id.receipt)
        val bill  = dialogBoxView.findViewById<RelativeLayout>(R.id.bill)
        val cutlery  = dialogBoxView.findViewById<RelativeLayout>(R.id.cutlery)
        val subsciption  = dialogBoxView.findViewById<RelativeLayout>(R.id.subsciption)
        val insurance  = dialogBoxView.findViewById<RelativeLayout>(R.id.insurance)
        val rent  = dialogBoxView.findViewById<RelativeLayout>(R.id.rent)
        val shopping  = dialogBoxView.findViewById<RelativeLayout>(R.id.shopping)
        val loadingProgressBar = dialogBoxView.findViewById<ProgressBar>(R.id.loadingProgressBar)

        tax.setOnClickListener {
            tag = tax.tag.toString()
            selectedImage.setImageResource(R.drawable.tax)
        }
        shopping.setOnClickListener {
            tag = tax.tag.toString()
            selectedImage.setImageResource(R.drawable.shopping_basket)
        }

        rent.setOnClickListener {
            tag = rent.tag.toString()
            selectedImage.setImageResource(R.drawable.house)
        }

        receipt.setOnClickListener {
            tag = receipt.tag.toString()
            selectedImage.setImageResource(R.drawable.receipt)
        }

        bill.setOnClickListener {
            tag = bill.tag.toString()
            selectedImage.setImageResource(R.drawable.bill)
        }

        cutlery.setOnClickListener {
            tag = cutlery.tag.toString()
            selectedImage.setImageResource(R.drawable.cutlery)
        }

        subsciption.setOnClickListener {
            tag = subsciption.tag.toString()
            selectedImage.setImageResource(R.drawable.subscription)
        }

        insurance.setOnClickListener {
            tag = insurance.tag.toString()
            selectedImage.setImageResource(R.drawable.insurance)
        }






        dialog = BottomSheetDialog(this,R.style.categoryBottomSheetTheme)
        dialog.setContentView(dialogBoxView)
        val databaseReference = FirebaseDatabase.getInstance().reference
        submitCategory.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            submitCategory.visibility = View.GONE
            if (categoryName.text.toString() != null){
                if (uid != null) {
                    val userRef = databaseReference.child("users").child(uid)
                    userRef.child("Categoryname").child(categoryName.text.toString()).child("icon").setValue(tag)
                        .addOnSuccessListener {
                            // Data added successfully
                            Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_LONG).show()
                            loadingProgressBar.visibility = View.GONE // Hide the loading indicator
                            dialog.dismiss() // Close the bottom sheet upon success
                        }
                        .addOnFailureListener {
                            // Handle the failure
                            Toast.makeText(this, it.message.toString(), Toast.LENGTH_LONG).show()
                            loadingProgressBar.visibility = View.GONE // Hide the loading indicator
                        }
                    // You can add more data under the user's UID here
                }
            }else{
                Toast.makeText(this, "Fill category name first", Toast.LENGTH_LONG).show()
            }

        }



        dialog.show()

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