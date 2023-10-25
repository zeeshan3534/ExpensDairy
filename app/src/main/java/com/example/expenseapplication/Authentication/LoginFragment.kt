package com.example.expenseapplication.Authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.expenseapplication.MainPageFragments.MainActivity

import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class LoginFragment : Fragment() {

    private var uid:String = ""
    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)
        val progressBar = binding.loadingProgressBar
        firebaseAuth = FirebaseAuth.getInstance()


        ///FOrgot password

        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.forgotPassWord)
        }

        binding.back.setOnClickListener {
            activity?.finish()
        }

        //SignUp navigation


        binding.signUp.setOnClickListener {


            findNavController().navigate(R.id.registerFragment)
        }


        ////login Functionality
        binding.login.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            binding.login.visibility = View.INVISIBLE
            val email = binding.editText.text.toString()
            val password = binding.editText3.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                        progressBar.visibility = View.GONE
                        startActivity(Intent(requireContext(),MainActivity::class.java))

                }.addOnFailureListener { error->
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),error.message.toString(),Toast.LENGTH_LONG).show()
                }
//                    .addOnCompleteListener { task->
//                    if(task.isSuccessful){
//                        startActivity(Intent(requireContext(),MainActivity::class.java))
//                    }
//
//
//                }
//                val db = FirebaseFirestore.getInstance()
//                db.collection("users")
//                    .whereEqualTo("email",email)
//                    .whereEqualTo("password",password)
//                    .get()
//                    .addOnSuccessListener {querySnapshot ->
//                        for (document in querySnapshot) {
//                            // A document with the provided email and password was found
//                            uid = document.id // Get the UID of the user
//                            // You can proceed with your login logic here
//                            Log.d("user ID",uid)
//                            break // Exit the loop since you've found a matching user
//                        }
//
//                    }
//                    .addOnFailureListener{error->
//                        Toast.makeText(requireContext(),"NotFound",Toast.LENGTH_LONG).show()
//
//                    }
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}