package com.impax.mgeni.commons

import androidx.core.net.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateFunction
{

    fun getDateFromatedString(inputDate: String?): String?
    {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(inputDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: java.text.ParseException) {
            e.printStackTrace()
        }
        if (date == null) {
            return ""
        }
        val convetDateFormat = SimpleDateFormat("HH:mm:ss")
        return convetDateFormat.format(date)
    }


}
