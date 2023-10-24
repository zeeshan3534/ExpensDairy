package com.example.expenseapplication.Authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.expenseapplication.Models.userDataStruct

import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.FragmentRegisterBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class RegisterFragment : Fragment() {


    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var storedVerifcationId : String = ""
    private var resendToken : String = ""
    private lateinit var callbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var name:String = ""
    private var email:String = ""
    private var phoneNumber:String = ""
    private var password:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        binding.back.setOnClickListener {
            activity?.finish()
        }
//        return inflater.inflate(R.layout.fragment_register, container, false)

        FirebaseApp.initializeApp(requireContext())
        firebaseAuth = FirebaseAuth.getInstance()


        binding.login.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        binding.signUpButton.setOnClickListener {

            name = binding.editText.text.toString()
            email = binding.editText3.text.toString()
            phoneNumber = binding.editText4.text.toString()
            password = binding.editText2.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty() && password.isNotEmpty()){

                firebaseAuth.createUserWithEmailAndPassword(email,password)   .addOnSuccessListener {
                    // The user account has been created successfully.

                    Toast.makeText(requireContext(),"Successfully done",Toast.LENGTH_LONG).show()
                    var bundle = Bundle()
                    bundle.putSerializable("data", userDataStruct(name,email,phoneNumber,password))
                    findNavController().navigate(
                        R.id.otpFragment,
                        bundle
                    )

                    /////Storing the User data in firestore
                    val user = firebaseAuth.currentUser
                    val db = Firebase.firestore
                    if(user!= null){
                        val uid = user.uid
                        val userData = hashMapOf(
                            "name" to "${name}",
                            "email" to "${email}",
                            "phoneNumber" to "${phoneNumber}"
                        )



                        val userRef = db.collection("users").document(uid)


                        userRef.set(userData)
                            .addOnSuccessListener {
                                Toast.makeText(requireContext(),"Data Added SuccessFully",Toast.LENGTH_LONG).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(requireContext(),it.message.toString(),Toast.LENGTH_LONG).show()
                            }
                    }



                }
                    .addOnFailureListener {error->
                        Toast.makeText(requireContext(),error.message.toString(),Toast.LENGTH_LONG).show()
                        // The user account could not be created.
                    }
//                startPhoneNumberVerification(phoneNumber )
            }
            else{
                Toast.makeText(requireContext(),"Some field is not filled",Toast.LENGTH_LONG).show()
            }

        }


        ///Otp Firebase Phone authentication
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.d("verification Start","Starting")

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                TODO("Not yet implemented")
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                storedVerifcationId = verificationId
                resendToken = token.toString()

                val userModel = userDataStruct(name,email,phoneNumber,password,storedVerifcationId)

                var bundle = Bundle()
                bundle.putSerializable("data", userModel)
                findNavController().navigate(
                    R.id.otpFragment,
                    bundle
                )
////                val action = SourceFragmentDirections.actionSourceFragmentToDestinationFragment(dataModel)
////                findNavController().navigate(action)
            }

        }
        return binding.root
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
            val options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(30L,TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(callbacks)
                .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }


}