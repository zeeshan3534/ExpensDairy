package com.example.expenseapplication.Authentication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.expenseapplication.Models.userDataStruct

import com.example.expenseapplication.R

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [OtpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtpFragment : Fragment() {
    private lateinit var timer: CountDownTimer
//    private lateinit var resendButton: TextView
    private var timerIsRunning = false
    private var signUpModel: userDataStruct? = null
    private lateinit var callbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var flag:Boolean = false
    private var verification:String = ""
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getWindow().setSoftIntMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        arguments.let {
            signUpModel = it!!.getSerializable("data") as userDataStruct

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_otp, container, false)

          val otpEditText: EditText = view.findViewById(R.id.otpEditText)
          val buttonVerify :RelativeLayout = view.findViewById(R.id.relativeLayout)
          val phoneNumber :TextView = view.findViewById(R.id.phoneNumber)
          val resend :TextView = view.findViewById(R.id.resendTask)
          val counter :TextView = view.findViewById(R.id.counter)


        val initialTimeInMillis = 30 * 1000

        timer = object : CountDownTimer(initialTimeInMillis.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Update UI with remaining time
                val secondsRemaining = millisUntilFinished / 1000
                counter.setText(counter.toString())
            }

            override fun onFinish() {
                // Timer has finished, you can handle this event (e.g., show a message)

                resend.setVisibility(View.VISIBLE)
                timerIsRunning = false
            }
        }


        phoneNumber.setText(signUpModel!!.phone.toString())
        
        otpEditText.requestFocus()
        otpEditText.requestFocus()
        otpEditText.showSoftInputOnFocus = true
        val dot1 = view.findViewById<TextView>(R.id.dot1)
        val dot2= view.findViewById<TextView>(R.id.dot2)
        val dot3= view.findViewById<TextView>(R.id.dot3)
        val dot4= view.findViewById<TextView>(R.id.dot4)
        val dot5= view.findViewById<TextView>(R.id.dot5)
        val dot6 = view.findViewById<TextView>(R.id.dot6)
        val dotsViews = arrayOf(dot1, dot2, dot3, dot4, dot5, dot6)
//

         otpEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val otp = s.toString()
                for (i in 0 until 6) {
                    if (i < otp.length) {
                        dotsViews[i].background = null
                        dotsViews[i].setText(otp[i].toString())
                    } else {
                        dotsViews[i].setBackgroundResource(R.drawable.otp_view)
                        dotsViews[i].text = ""
                    }
                }
            }
        })

//
        buttonVerify.setOnClickListener {
            findNavController().navigate(R.id.first_pin_message)

//            if (otpEditText.text.isNotEmpty()) {
//                if (!flag) {
//                    verifyTheOtp(otpEditText.text.toString(), signUpModel!!.verificationId,signUpModel!!)
//                }else{
//                    verifyTheOtp(otpEditText.text.toString(), verification,signUpModel!!)
//
//                }
//            }else{
//                Toast.makeText(requireContext(),"Please fill the Otp",Toast.LENGTH_LONG).show()
//            }
        }


        resend.setOnClickListener {

            if (!timerIsRunning) {
                // Start the timer
                timer.start()
                timerIsRunning = true

                // Trigger the OTP resend request here
                // Replace with your actual code to send the OTP
                flag = true
                reSendTheData(signUpModel!!.phone)
            }

        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.d("verification Start","Starting")

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                TODO("Not yet implemented")
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                verification = verificationId


            }

        }


        return view
    }

    private fun reSendTheData(phoneNumber:String) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(30L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

//    private fun verifyTheOtp(otp: String, verificationId: String,userModel:userDataStruct) {
//        val credential:PhoneAuthCredential = PhoneAuthProvider.getCredential(
//            verificationId,otp
//        )
//        SignWithAuthCredential(credential,userModel)
//
//    }

    private fun SignWithAuthCredential(credential: PhoneAuthCredential, userModel: userDataStruct) {
//           val auth = FirebaseAuth.getInstance()
//           auth.signInWithCredential(credential).addOnCompleteListener {task->
//               if(task.isSuccessful){
                   Toast.makeText(requireContext(),"DOne",Toast.LENGTH_SHORT
                   ).show()

//                   val db = Firebase.firestore
                   val db = Firebase.firestore
                   val usersCollection = db.collection("users")

                   val user = hashMapOf(
                       "name" to userModel.name,
                       "email" to userModel.email,
                       "phoneNumber" to userModel.phone,

//                               userModel.password,
                       // Add more fields as needed
                   )
                   val firebaseUser = Firebase.auth.currentUser
                   if (firebaseUser != null) {
                       // Use the user's unique ID as the document ID
                       val userDocument = usersCollection.document(firebaseUser.uid)

                       userDocument.set(user)
                           .addOnSuccessListener {
                               // Data has been successfully saved
                               // Handle success here, for example, show a toast or navigate to another screen
                               Toast.makeText(requireContext(), "Data saved successfully", Toast.LENGTH_SHORT).show()
                           }
                           .addOnFailureListener { e ->
                               // Handle errors
                               Log.w(TAG, "Error adding document", e)
                               // Handle error, for example, show an error message
                               Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                           }


                       findNavController().navigate(R.id.first_pin_message)
                   } else {
                       // User is not signed in, handle this case as needed
                   }

//               }
//           }

    }



}