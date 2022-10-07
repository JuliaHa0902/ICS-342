package edu.metrostate.assignment1

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun Long.toDate(): String {
    val formatter = DateTimeFormatter.ofPattern("MMM dd")
    val dateTime = LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.of("-5"))
    return formatter.format(dateTime)
}

fun Long.toTime(): String {
    val formatter = DateTimeFormatter.ofPattern("h:mm")
    val dateTime = LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.of("-5"))
    return formatter.format(dateTime)
}