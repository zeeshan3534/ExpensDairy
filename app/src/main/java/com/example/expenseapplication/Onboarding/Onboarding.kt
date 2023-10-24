package com.example.expenseapplication.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.expenseapplication.Authentication.AuthenticaitonActivity
import com.example.expenseapplication.Models.onBoardingItems
import com.example.expenseapplication.R

class Onboarding : AppCompatActivity() {



    private val onboardingItems = listOf(
        onBoardingItems(
            R.drawable.first_onboarding,
            R.string.first_Onboarding_title,
            R.string.first_onboarding_describtion
        ),
        onBoardingItems(
            R.drawable.second_onboarding,
            R.string.second_Onboarding_title,
            R.string.second_onboarding_describtion
        ),
        onBoardingItems(
            R.drawable.third_onboarding,
            R.string.third_Onboarding_title,
            R.string.third_onboarding_describtion
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        val dot1: ImageView = findViewById(R.id.dot1)
        val dot2: ImageView = findViewById(R.id.dot2)
        val dot3: ImageView = findViewById(R.id.dot3)
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        val onboardingAdapter = OnboardingAdapter(onboardingItems)
        viewPager2.adapter = onboardingAdapter


        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position ==0){
                    dot1.setBackgroundResource(R.drawable.on_selected_onboarding)
                    dot2.setBackgroundResource(R.drawable.on_unselected_onboarding)
                    dot3.setBackgroundResource(R.drawable.on_unselected_onboarding)
                }else if (position ==1){
                    dot1.setBackgroundResource(R.drawable.on_unselected_onboarding)
                    dot2.setBackgroundResource(R.drawable.on_selected_onboarding)
                    dot3.setBackgroundResource(R.drawable.on_unselected_onboarding)
                }else{
                    dot1.setBackgroundResource(R.drawable.on_unselected_onboarding)
                    dot2.setBackgroundResource(R.drawable.on_unselected_onboarding)
                    dot3.setBackgroundResource(R.drawable.on_selected_onboarding)

                }
            }
        })



        ////Button Functionalities of Signin and SignUp

        val signUnButton = findViewById<RelativeLayout>(R.id.button)
        val LoginButton = findViewById<RelativeLayout>(R.id.button2)


        LoginButton.setOnClickListener {
            val intent = Intent(this, AuthenticaitonActivity::class.java)
            intent.putExtra("fragmentToLoad", "loginFragment")
            startActivity(intent)
            finish()
        }
        signUnButton.setOnClickListener{
            val intent = Intent(this, AuthenticaitonActivity::class.java)
            startActivity(intent)
            finish()
        }





    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()


    }


}
