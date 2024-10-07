package com.learn.fgo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Servant (
    val name: String,
    val classServant: String,
    val description: String,
    val photo: String
) : Parcelable