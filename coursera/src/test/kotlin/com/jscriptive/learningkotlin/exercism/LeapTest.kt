package com.jscriptive.learningkotlin.exercism

import org.junit.Assert.*
import org.junit.Test

class LeapTest {

    @Test
    fun yearNotDivisibleBy4() {
        assertFalse(Year(2015).isLeap)
    }

    @Test
    fun yearDivisibleBy4NotDivisibleBy100() {
        assertTrue(Year(1996).isLeap)
    }

    @Test
    fun yearDivisibleBy100NotDivisibleBy400() {
        assertFalse(Year(2100).isLeap)
    }

    @Test
    fun yearDivisibleBy400() {
        assertTrue(Year(2000).isLeap)
    }

}
