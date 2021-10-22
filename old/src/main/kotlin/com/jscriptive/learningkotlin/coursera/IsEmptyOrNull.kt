package com.jscriptive.learningkotlin.coursera

fun main() {
    val s1: String? = null
    val s2: String? = ""
    s1.isEmptyOrNull() eq true
    s2.isEmptyOrNull() eq true

    val s3 = "   "
    s3.isEmptyOrNull() eq false
}

fun String?.isEmptyOrNull(): Boolean =
    this == null || this == ""

infix fun Boolean.eq(other: Boolean) =
    if (this == other) println("OK")
    else println("NOK")
