package com.jscriptive.learningkotlin.generics


fun main(args: Array<String>) {
    append(StringBuilder("string1 "), StringBuilder("string2 "))
}

fun <T> append(item1: T, item2: T)
        where T : CharSequence, T : Appendable {
    println("Result is ${item1.append(item2)}")
}