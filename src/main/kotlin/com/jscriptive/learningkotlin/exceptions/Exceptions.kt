package com.jscriptive.learningkotlin.exceptions

fun main(args: Array<String>) {

    println(getNum("22"))
    println(getNum("22.5") ?: "I can't print the result")
    println(getNum("22.5") ?: throw IllegalArgumentException("Number isn't an Int"))
}

fun getNum(str: String): Int? {
    return try {
        Integer.parseInt(str)
    }
    catch (e: NumberFormatException) {
        null
    }
    finally {
        println("I'm in the finally")
    }
}