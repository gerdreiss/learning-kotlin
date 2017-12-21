package com.jscriptive.learningkotlin.nullreferences

private class Country {
    var countryCode: String? = null
}
private class Address(street: String, place: String) {
    var country: Country? = null
}
private class BankBranch(id: Int) {
    var address: Address? = null
    fun ad(): Address? {
        return address
    }
}

fun main(args: Array<String>) {

    val str: String? = null
    println("What happens when we do this: ${str?.toUpperCase()}")

    val bankBranch = BankBranch(1)
    val countryCode: String? = bankBranch.address?.country?.countryCode
    println("And what happens when we do this: ${countryCode?.toUpperCase()}")

    val countryCode2 = countryCode ?: "unknown"
    println("And this: ${countryCode2.toUpperCase()}")

    val something: Any = arrayOf(1, 2, 3, 4)
    val str2 = something as? String
    println(str2)

    val ints = something as Array<*>
    val i = ints[0] as Int
    println(i)

    //str = "Not null anymore"
    // println(str!!.toUpperCase()) // throws KotlinNullPointerException

    println("print with let")
    // istead of if (str != null) :
    str.let { println(it) }

    println(str == "") // == is null safe
}
