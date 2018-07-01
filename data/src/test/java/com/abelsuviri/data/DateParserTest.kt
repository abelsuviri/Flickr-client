package com.abelsuviri.data

import com.abelsuviri.data.utils.DateParser
import org.junit.Assert
import org.junit.Test

/**
 * @author Abel Suviri
 */

class DateParserTest {
    @Test
    fun parse_date_behaves_correct() {
        val date = "2018-06-30T18:22:59Z"
        val parsedDate = DateParser.parseDate(date)

        Assert.assertEquals(parsedDate, "30/06/2018 18:22")
    }
}