package com.example.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class StringsTest {
    @Test
    fun replace() {
        assertEquals("bla", Strings.remove("blafoo", "foo"))
    }
}