package com.example.expenseapplication.Adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expenseapplication.Models.Categories
import com.example.expenseapplication.R

class homeCategoryAdaptor(private var data: List<Categories>):RecyclerView.Adapter<homeCategoryAdaptor.ViewHolder>(){


    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemTitle = itemView.findViewById<TextView>(R.id.iv_title)
        val itemDetail = itemView.findViewById<TextView>(R.id.iv_description)
        val itemPicture = itemView.findViewById<ImageView>(R.id.iv_image)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.home_adaptor_view,parent,false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.itemTitle.text = data[position].categoryName

        when(data[position].icon){
            "tax" -> holder.itemPicture.setImageResource(R.drawable.tax)
            "insurance" ->holder.itemPicture.setImageResource(R.drawable.insurance)
            "receipt" ->holder.itemPicture.setImageResource(R.drawable.receipt)
            "house" ->holder.itemPicture.setImageResource(R.drawable.house)
            "bill" -> holder.itemPicture.setImageResource(R.drawable.bill)
            "subscription" -> holder.itemPicture.setImageResource(R.drawable.subscription)
            "cutlery" -> holder.itemPicture.setImageResource(R.drawable.cutlery)
            else -> holder.itemPicture.setImageResource(R.drawable.shopping_basket  )

        }
    }
}