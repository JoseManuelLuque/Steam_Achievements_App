package com.jluqgon214.logrossteam.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTimestamp(timestamp: Long): String {
    val date = Date(timestamp * 1000) // Convertir de segundos a milisegundos
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return format.format(date)
}