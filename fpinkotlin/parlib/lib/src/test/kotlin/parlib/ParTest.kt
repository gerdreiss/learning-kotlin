package parlib

import arrow.core.firstOrNone
import arrow.core.getOrElse
import kotlin.test.Test
import kotlin.test.assertEquals

class ParTest {
    @Test
    fun testParSum() {
        fun sum(ints: List<Int>): Int =
            if (ints.size <= 1)
                ints.firstOrNone().getOrElse { 0 }
            else {
                val index = ints.size / 2
                val sumL: Par<Int> = unit { sum(ints.take(index)) }
                val sumR: Par<Int> = unit { sum(ints.drop(index)) }
                sumL.get + sumR.get
            }

        assertEquals(45, sum(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)))
    }
}
