package com.dicoding.fgo_servant_info

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Servant(
    val name: String,
    val description: String,
    val noblePhantasm: String,
    val npDescription: String,
    val photoIcon: Int,
    val photo: Int
) : Parcelable
