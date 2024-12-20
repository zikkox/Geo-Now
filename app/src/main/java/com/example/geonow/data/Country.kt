package com.example.geonow.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: Name,
    val continents: List<String>,
    val flags: Flag
) : Parcelable

@Parcelize
data class Name(
    val common: String,
    val official: String?
) : Parcelable

@Parcelize
data class Flag(
    val png: String
) : Parcelable