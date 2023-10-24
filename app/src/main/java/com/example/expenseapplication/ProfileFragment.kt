package com.example.expenseapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expenseapplication.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {



    private lateinit var bindind: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindind =  FragmentProfileBinding.inflate(layoutInflater)


////////////////Payment method


        /////////////
        return bindind.root
    }


}