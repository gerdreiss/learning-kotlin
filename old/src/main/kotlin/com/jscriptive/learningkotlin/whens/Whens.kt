package com.jscriptive.learningkotlin.whens

import java.math.BigDecimal
import java.util.*

fun main(args: Array<String>) {

    val something: Any = BigDecimal("1.2")
    val eval: Any = when (something) {
        is String -> something.uppercase()
        is BigDecimal -> something.remainder(BigDecimal.ONE)
        is Int -> "${something - 1}"
        else -> "Huh?"
    }

    println(eval.toString())

    val season = Season.WINTER
    val result = when (season) {
        Season.SPRING -> "Spring"
        Season.SUMMER -> "Summer"
        Season.AUTUMN -> "Fall"
        Season.WINTER -> "Brrr"
    }

    println(result)

    val num1 = Random().nextInt(100)
    val num2 = Random().nextInt(100)
    when {
        num1 < num2 -> println("$num1 < $num2")
        num1 > num2 -> println("$num1 > $num2")
        else -> println("$num1 = $num2")
    }
}

enum class Season {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER
}