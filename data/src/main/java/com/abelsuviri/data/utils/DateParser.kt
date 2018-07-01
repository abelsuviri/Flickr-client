package com.abelsuviri.data.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Abel Suviri
 */

class DateParser {
    companion object {
        /**
         * @return a yyyy-MM-dd'T'HH:mm:ss'Z' date in dd/MM/yyyy HH:mm format
         */
        fun parseDate(date: String): String {
            return try {
                val currentFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                val currentDate = currentFormat.parse(date)

                val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                outputFormat.timeZone = TimeZone.getDefault()
                outputFormat.format(currentDate)
            } catch (ignore: Exception) {
                date
            }
        }
    }
}