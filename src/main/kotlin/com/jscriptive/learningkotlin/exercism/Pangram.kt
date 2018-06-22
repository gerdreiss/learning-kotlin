package com.jscriptive.learningkotlin.exercism

object Pangram {

    fun isPangram(s: String): Boolean =
            ('a'..'z').all { s.contains(it, ignoreCase = true) }

    fun isPangram2(s: String): Boolean =
            "abcdefghijklmnopqrstuvwxyz".toSortedSet() == s.toLowerCase().filter { it.isLetter() }.toSortedSet()

}
