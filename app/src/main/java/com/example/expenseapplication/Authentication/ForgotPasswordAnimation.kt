package com.example.expenseapplication.Authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.FragmentForgotPasswordAnimationBinding


class ForgotPasswordAnimation : Fragment() {



    private lateinit var binding: FragmentForgotPasswordAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentForgotPasswordAnimationBinding.inflate(layoutInflater)
        val animation = binding.forgotanim
        animation.playAnimation()
        binding.backToLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        return binding.root
    }


}