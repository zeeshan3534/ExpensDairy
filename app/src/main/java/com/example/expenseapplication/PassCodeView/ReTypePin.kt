package com.example.expenseapplication.PassCodeView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.expenseapplication.DataStore.PinDataStore
import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.FragmentReTypePinBinding
//import com.example.expenseapplication.databinding.FragmentReTypePinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReTypePin : Fragment() {

    private lateinit var binding: FragmentReTypePinBinding
    private var pin: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { data ->

            pin = data.getString("pin")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val pinManager = PinDataStore(requireContext())


        binding = FragmentReTypePinBinding.inflate(layoutInflater)
        binding.done.setOnClickListener {
            val pintext = binding.pinview.value
            if (pintext.count() == 6) {
                if (pintext == pin) {
                    CoroutineScope(Dispatchers.IO).launch {
                        pinManager.savePin(pintext)

                        val firebaseAuth = FirebaseAuth.getInstance()
                        val user = firebaseAuth.currentUser
                        val db = Firebase.firestore
                        if (user!=null){
                            val uid = user.uid
                            val userPin = hashMapOf(
                                "passKey" to pintext.toString()
                            )

                            val userRef = db.collection("users").document(uid)


                            userRef.set(userPin)
                                .addOnSuccessListener {
                                    Toast.makeText(requireContext(),"Pin saved SuccessFully",Toast.LENGTH_LONG).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(requireContext(),it.message.toString(),Toast.LENGTH_LONG).show()
                                }
                        }
                    }

                    findNavController().navigate(R.id.allSet)
                } else {
                    Toast.makeText(requireContext(), "Your Passcode is not same", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter the Pin First", Toast.LENGTH_LONG)
                    .show()
            }
        }


        return binding.root
        // Inflate the layout for this fragment

    }

}