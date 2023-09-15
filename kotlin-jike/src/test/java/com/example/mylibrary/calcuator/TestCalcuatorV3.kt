package com.example.mylibrary.calcuator

import com.example.kotlins._04calcuate.Calcuate3
import org.junit.Test
import kotlin.test.assertEquals

class TestCalcuatorV3 {

    @Test
    fun testCalcuator() {
        val calcuate3 = Calcuate3();
        val result = calcuate3.calcuate("333333333333333333333333331+2")
        assertEquals("333333333333333333333333333", result)

        val result2 = calcuate3.calcuate("12+2")
        assertEquals("114", result2)
    }
}