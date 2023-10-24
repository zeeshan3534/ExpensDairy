package com.example.expenseapplication.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieAnimationView
import com.example.expenseapplication.Authentication.AuthenticaitonActivity
import com.example.expenseapplication.MainPageFragments.MainActivity
import com.example.expenseapplication.Onboarding.Onboarding
import com.example.expenseapplication.PassCodeView.PinView
import com.example.expenseapplication.R
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val anim = findViewById<LottieAnimationView>(R.id.splash)

        anim.playAnimation()

        Handler().postDelayed(
            {

                val currentUser = FirebaseAuth.getInstance().currentUser

                if(currentUser != null){
                    val intent = Intent(this, PinView::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    val intent = Intent(this, Onboarding::class.java)
                    startActivity(intent)
                    finish()
                }
//                val intent = Intent(this, Onboarding::class.java)
//                    startActivity(intent)
//                    finish()

            },6000
        )
    }
}