package com.tugas.www.finder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun changeDateFormat(firstFormat: String, newFormat:String, dateString :String): String {
    var format = SimpleDateFormat(firstFormat, Locale.getDefault())
    val date = format.parse(dateString)
    format = SimpleDateFormat(newFormat, Locale.getDefault())
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

fun showLimitNotification(context: FragmentActivity, title: String, message: String, notifId: Int) {
    val CHANNEL_ID = "Channel_1"
    val CHANNEL_NAME = "Limit Expense Channel"

    val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_baseline_notifications_24)
        .setContentTitle(title)
        .setContentText(message)
        .setVibrate(longArrayOf(1000, 1000, 1000))
        .setColor(ContextCompat.getColor(context, android.R.color.transparent))

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(CHANNEL_ID,
        CHANNEL_NAME,
        NotificationManager.IMPORTANCE_DEFAULT)

        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(1000, 1000, 1000)
        builder.setChannelId(CHANNEL_ID)
        notificationManagerCompat.createNotificationChannel(channel)
    }

    val notification = builder.build()

    notificationManagerCompat.notify(notifId, notification)
}