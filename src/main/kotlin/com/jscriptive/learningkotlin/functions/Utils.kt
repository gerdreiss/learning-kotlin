package com.jscriptive.learningkotlin.functions

class Utils {
    // the java way
    fun upperFirstAndLast(str: String): String {
        //val len = str.length
        //val upperFirst = str.substring(0, 1).toUpperCase() + str.substring(1)
        //return upperFirst.substring(0, len - 1) + upperFirst.substring(len - 1, len).toUpperCase()
        return str.capitalize().reversed().capitalize().reversed()
    }
}