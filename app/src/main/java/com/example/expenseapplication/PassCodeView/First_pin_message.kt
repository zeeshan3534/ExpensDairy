package com.example.expenseapplication.PassCodeView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.FragmentFirstPinMessageBinding

class First_pin_message : Fragment() {

    private lateinit var binding: FragmentFirstPinMessageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var password =""
//        val view =  inflater.inflate(R.layout.fragment_first_pin_message, container, false)
        binding = FragmentFirstPinMessageBinding.inflate(layoutInflater)



        binding.next.setOnClickListener{
            password = binding.pinview.value
            if (password.count() == 6){
                var bundle = Bundle()
                bundle.putString("pin",password.toString())
                findNavController().navigate(R.id.reTypePin,bundle)
            }else{
                Toast.makeText(requireContext(),"Please fill the pin first",Toast.LENGTH_SHORT).show()
            }

        }


// remove last characters from input values and run animation when text is removed\
        return binding.root
    }


}