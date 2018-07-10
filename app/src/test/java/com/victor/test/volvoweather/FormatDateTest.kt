package com.victor.test.volvoweather

import com.victor.test.volvoweather.utils.trace
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class FormatDateTest {

    private fun getFormattedDateTime(time:Long): String {
        val justHour = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = Date(time*1000L)
        trace("Convertion just hour ${justHour.format(date)}")
        return justHour.format(date)
    }

    fun getFormattedDateTimeForAdapter(time:Long): String {
        val dayNameFormat = SimpleDateFormat("EEEEE", Locale.getDefault())
        val dayNumberFormat = SimpleDateFormat("dd", Locale.getDefault())
        val date = Date(time*1000L)
        val dayNameText = dayNameFormat.format(date)
        val dayNameNumber = dayNumberFormat.format(date)
        trace("Convertion to name and number :: $dayNameText $dayNameNumber")
        return "$dayNameText $dayNameNumber"
    }

    @Test
    fun `should format date and return hours and minutes`() {
        val today:Long = 1531157226
        val hour = "19:27"

        val hourRetrieved = getFormattedDateTime(today)
        assert(hour == hourRetrieved)
    }

    @Test
    fun `should retrieve day name and day number`() {
        val today:Long = 1531157226
        val result = getFormattedDateTimeForAdapter(today)
        assert(result == "tuesday 10")
    }
}