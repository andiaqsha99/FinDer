package com.tugas.www.finder

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun changeDateFormat(firstFormat: String, newFormat:String, dateString :String): String {
    var format = SimpleDateFormat(firstFormat)
    val date = format.parse(dateString)
    format = SimpleDateFormat(newFormat)
    return format.format(date)
}

fun formatToRupiah(amount: Long): String {
    val locale = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(locale)
    return formatRupiah.format(amount)
}

fun getWeekOfDate(date: String): String {
    val array = date.split("-").map { it.toInt() }
    val calendar: Calendar = Calendar.getInstance()
    calendar.set(array[0], array[1]-1, array[2])
    val weekOfYear: Int = calendar.get(Calendar.WEEK_OF_YEAR)
    return weekOfYear.toString()
}