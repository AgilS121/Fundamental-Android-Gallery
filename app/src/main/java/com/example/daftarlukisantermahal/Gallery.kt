package com.example.daftarlukisantermahal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gallery(
    val name: String,
    val description: String,
    val photo: Int,
    val story: String,
    val author: String,
    val year: String,
    val url: String
) : Parcelable
