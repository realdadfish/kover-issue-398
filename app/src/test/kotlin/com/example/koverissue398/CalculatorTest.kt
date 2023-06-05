package com.example.koverissue398

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {
    @Test
    fun testAdd() {
        assertEquals(4, Calculator.add(1, 3))
    }

    @Test
    fun testMul() {
        assertEquals(12, Calculator.mul(3, 4))
    }
}