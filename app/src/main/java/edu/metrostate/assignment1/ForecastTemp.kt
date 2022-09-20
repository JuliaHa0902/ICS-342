package edu.metrostate.assignment1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastTemp (
    val day: Float,
    val min: Float,
    val max: Float
    ): Parcelable