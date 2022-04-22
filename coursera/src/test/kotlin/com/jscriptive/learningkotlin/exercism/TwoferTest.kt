package com.jscriptive.learningkotlin.exercism

import org.junit.Test
import org.junit.Assert.*

class TwoferTest {

    @Test
    fun noNameGiven() {
        assertEquals("One for you, one for me.", twofer("you"))
    }

    @Test
    fun aNameGiven() {
        assertEquals("One for Alice, one for me.", twofer("Alice"))
    }

    @Test
    fun anotherNameGiven() {
        assertEquals("One for Bob, one for me.", twofer("Bob"))
    }

    @Test
    fun emptyStringGiven() {
        assertEquals("One for , one for me.", twofer(""))
    }

}
