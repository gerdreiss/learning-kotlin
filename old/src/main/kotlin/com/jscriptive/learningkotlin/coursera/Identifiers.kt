package com.jscriptive.learningkotlin.coursera

// mine, which I like better
fun isValidIdentifier(s: String): Boolean =
    s.matches("^[_a-zA-Z]{1}[_a-zA-Z0-9]+$".toRegex())

// coursera, ugly as hell
fun isValidIdentifier0(s: String): Boolean {
    fun isValidCharacter(ch: Char) =
        ch == '_' || ch.isLetterOrDigit() // ch in '0'..'0' || ch in 'a'..'z' || ch in 'A'..'Z'

    if (s.isEmpty() || s[0].isDigit())
        return false

    for (ch in s) {
        if (!isValidCharacter(ch)) return false
    }

    return true;
}

fun main() {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("__12"))   // true
    println(isValidIdentifier("_1_2"))   // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}
