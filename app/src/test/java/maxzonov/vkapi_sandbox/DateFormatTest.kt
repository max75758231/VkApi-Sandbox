package maxzonov.vkapi_sandbox

import maxzonov.vkapi_sandbox.utils.DateFormatter
import org.junit.Test

import org.junit.Assert.*

class DateFormatTest {

    @Test
    fun testingUnixDateToStringConversion() {
        val inputDate: Long = 1548689090
        val expectedDate = "28 января 2019"

        assertEquals(DateFormatter.convertDateToDayString(inputDate), expectedDate)
    }

    @Test
    fun testingUnixDateToTimeConversion() {
        val inputDate: Long = 1548689090
        val expectedTime = "18:24"
        assertEquals(DateFormatter.convertDateToTimeString(inputDate), expectedTime)
    }
}