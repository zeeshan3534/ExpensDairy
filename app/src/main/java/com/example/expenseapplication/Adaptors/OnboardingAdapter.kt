package com.example.expenseapplication.Adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expenseapplication.Models.onBoardingItems
import com.example.expenseapplication.R

class OnboardingAdapter(private val items: List<onBoardingItems>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>()   {


    inner class OnboardingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.imageView)
        private val titleTextView: TextView = view.findViewById(R.id.title)
        private val descriptionTextView: TextView = view.findViewById(R.id.description)

        fun bind(item: onBoardingItems) {
            imageView.setImageResource(item.imageResource)
            titleTextView.setText(item.title)
            descriptionTextView.setText(item.description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_screen_layout, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size



}