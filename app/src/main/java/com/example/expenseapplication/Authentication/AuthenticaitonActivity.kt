package com.example.expenseapplication.Authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expenseapplication.R

class AuthenticaitonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticaiton)


        val fragmentToLoad = intent.getStringExtra("fragmentToLoad")
        if (fragmentToLoad != null) {
            when (fragmentToLoad) {
                "loginFragment" -> supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, LoginFragment())
                    .commit()
                // Add more cases for other fragments as needed
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()


    }
}