package com.example.expenseapplication.Authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.FragmentForgotPassWordBinding
import com.google.firebase.auth.FirebaseAuth

class forgotPassWord : Fragment() {



   private lateinit var binding: FragmentForgotPassWordBinding
   private lateinit var auth: FirebaseAuth
    private var email:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentForgotPassWordBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()


        binding.forgotPass.setOnClickListener {
            email = binding.emailForgot.text.toString()
            if (email.isNotEmpty()){
                auth.sendPasswordResetEmail(email)
                    .addOnSuccessListener {task->
                        findNavController().navigate(R.id.forgotPasswordAnimation)
                    }
                    .addOnFailureListener {exception->
                        Toast.makeText(requireContext(), exception.message.toString(), Toast.LENGTH_LONG).show()
                    }
            }
            else{
                Toast.makeText(requireContext(),"Your field is empty",Toast.LENGTH_LONG).show()
            }

        }

        return binding.root
    }

}