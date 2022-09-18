package edu.metrostate.assignment1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast (
    val temp: String,
): Parcelable