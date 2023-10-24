package com.example.expenseapplication.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable



data class userDataStruct(
    val name:String,
    val email:String,
    val phone:String,
    val password:String,
    val verificationId:String=""
):Serializable
