package maxzonov.vkapi_sandbox.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

object DateFormatter {

    fun convertDateToDayString(date: Long): String {
        val defaultDate = Date(date * 1000L)

        val dateFormat = SimpleDateFormat("dd MMMM yyyy")

        return dateFormat.format(defaultDate)
    }

    fun convertDateToTimeString(date: Long): String {
        val defaultDate = Date(date * 1000L)

        val dateFormat = SimpleDateFormat("HH:mm")
        dateFormat.timeZone = TimeZone.getTimeZone("GMT+3")

        return dateFormat.format(defaultDate)
    }
}
