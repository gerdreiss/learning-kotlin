package com.jscriptive.learningkotlin.inheritance

interface Interfaces1 {

    val number1: Int

    fun func1(str: String): String
}

interface Interfaces2 : Interfaces1 {

    val number2: Int
        get() = number1 * 2

    fun func2(str: String): String
}

open class Somethings1 : Interfaces2 {
    override val number1: Int = 42
    //get() = 42
    override val number2: Int = 42

    override fun func1(str: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun func2(str: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}