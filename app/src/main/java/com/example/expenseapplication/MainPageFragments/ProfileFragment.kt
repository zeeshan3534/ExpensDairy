package com.example.expenseapplication.MainPageFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.expenseapplication.Onboarding.Onboarding
import com.example.expenseapplication.R
import com.example.expenseapplication.databinding.FragmentProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var dialog: BottomSheetDialog

    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater)


////////////////Payment method


        /////////////

        ///////////////Logout
        binding.Logout.setOnClickListener {
            openDialogForLogout()
        }
        /////////////////
        return binding.root
    }

    private fun openDialogForLogout() {
        val dialogBoxView = layoutInflater.inflate(R.layout.logout_dialouge,null)

        val confirmLogout = dialogBoxView.findViewById<RelativeLayout>(R.id.yes_logout)
        val cancelLogout = dialogBoxView.findViewById<RelativeLayout>(R.id.cancel_logout)
        val progressBar = dialogBoxView.findViewById<ProgressBar>(R.id.loadingProgressBar)





        dialog = BottomSheetDialog(requireContext(), R.style.categoryBottomSheetTheme)
        dialog.setContentView(dialogBoxView)

        cancelLogout.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            dialog.dismiss()
        }
        confirmLogout.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            dialog.dismiss()
            startActivity(Intent(requireContext(), Onboarding::class.java))
            activity?.finish()

        }



        dialog.show()
    }


}