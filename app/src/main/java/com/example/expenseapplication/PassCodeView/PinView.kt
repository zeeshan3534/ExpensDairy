package com.example.expenseapplication.PassCodeView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.expenseapplication.DataStore.PinDataStore
import com.example.expenseapplication.MainPageFragments.MainActivity
import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.ActivityPinViewBinding
import com.hanks.passcodeview.PasscodeView
import com.hanks.passcodeview.PasscodeView.PasscodeViewListener
import com.keijumt.passwordview.PasswordView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PinView : AppCompatActivity() {


    private lateinit var binding:ActivityPinViewBinding
    private var actualPin:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPinViewBinding.inflate(layoutInflater)
        setContentView(binding.root)




        CoroutineScope(Dispatchers.IO).launch {

            val pinManager = PinDataStore(this@PinView) // Initialize PinManager with a Context
            val pinFlow = pinManager.pinFlow
            pinFlow.collect { pin ->
                if (pin != null) {
                    val passcodeView = binding.passcodeView
                    Log.d("locaal passcode",actualPin.toString())
                    passcodeView.setPasscodeLength(6)
                        .setLocalPasscode(pin)
                    passcodeView.setListener(object : PasscodeView.PasscodeViewListener {
                        override fun onFail() {
                            // Handle incorrect passcode entry
                            Toast.makeText(this@PinView, "Incorrect passcode", Toast.LENGTH_SHORT).show()
                        }

                        override fun onSuccess(numbers: String?) {
                            // Handle correct passcode entry
//                Toast.makeText(this@MainActivity, "Passcode entered: $numbers", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@PinView,MainActivity::class.java))
                            finish()
                        }
                    })




                    actualPin = pin
                    Log.d("locaal passcode",pin.toString())
                } else {
                    Log.d("Actual Pin","the pin is empty")
                }
            }
        }





    }
}