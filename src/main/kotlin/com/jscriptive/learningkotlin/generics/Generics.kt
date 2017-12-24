package com.jscriptive.learningkotlin.generics

import java.math.BigDecimal


fun main(args: Array<String>) {
    append(StringBuilder("string1 "), StringBuilder("string2 "))

    println(getElementsOfType<String>(listOf("str", 1, BigDecimal.ONE)))
}

fun <T> append(item1: T, item2: T)
        where T : CharSequence, T : Appendable {
    println("Result is ${item1.append(item2)}")
}

inline fun <reified T> getElementsOfType(elms: List<Any>): List<T> =
        elms.filter { it is T }
                .map { it as T }


open class Flower
class Rose : Flower()

/* covariant */
class Garden<out T : Flower>(vararg flowers: T) {

    private val _flowers: MutableList<@UnsafeVariance T> = mutableListOf(*flowers)

    fun pickFlower(index: Int): T = _flowers[index]

    fun plantFlower(flower: @UnsafeVariance T): Boolean =
            _flowers.add(flower)

}
