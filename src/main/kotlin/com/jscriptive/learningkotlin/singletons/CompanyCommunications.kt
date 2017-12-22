package com.jscriptive.learningkotlin.singletons

import java.time.Year

fun main(args: Array<String>) {
    println(CompanyCommunications.getTagLine())
    println(CompanyCommunications.getCopyrightLine())

    println(SomeClass.accessPrivateVar())

    val someClass1 = SomeClass.justAssign("this is the string as is")
    val someClass2 = SomeClass.upperOrLowerCase("this isn't the string as is", false)

    println(someClass1.someString)
    println(someClass2.someString)
}

object CompanyCommunications {

    val currentYear = Year.now().value

    fun getTagLine() = "Our company blah"
    fun getCopyrightLine() = "Copyright \u00A9 $currentYear Our company. All rights reserved."

}

class SomeClass private constructor(val someString: String) {

    //val someString: String
    //constructor(str: String) {
    //    someString = str
    //}
    //constructor(str: String, lowerCase: Boolean) {
    //    someString = if (lowerCase) str.toLowerCase() else str.toUpperCase()
    //}

    companion object {
        private val privateVar = 6

        fun accessPrivateVar() = "I'm accessing privateVar: $privateVar"

        fun justAssign(str: String) = SomeClass(str)
        fun upperOrLowerCase(str: String, lowerCase: Boolean): SomeClass {
            return SomeClass(
                    if (lowerCase) str.toLowerCase() else str.toUpperCase()
            )
        }
    }

}
