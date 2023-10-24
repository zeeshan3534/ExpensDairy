package com.example.expenseapplication.Authentication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.expenseapplication.MainPageFragments.MainActivity
import com.example.expenseapplication.Onboarding.Onboarding

import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.FragmentAllSetBinding

class allSet : Fragment() {

    private lateinit var binding: FragmentAllSetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllSetBinding.inflate(layoutInflater)
        val anim = binding.allset

        anim.playAnimation()

        Handler().postDelayed(
            {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)

            },5000
        )
        return binding.root
        // Inflate the layout for this fragment

    }


}