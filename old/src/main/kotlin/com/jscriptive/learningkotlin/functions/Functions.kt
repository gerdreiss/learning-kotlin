package com.jscriptive.learningkotlin.functions

fun main(args: Array<String>) {

    println(labelMultiply(3, 4, "The result is: "))

    printBrands("My car",
            Car("Ford", "Capri"),
            Car("VW", "Golf"))

    val cars = arrayOf(
            Car("Ford", "Capri"),
            Car("VW", "Golf"))

    printBrands("Prefix: ", *cars)


    val str = "this is all in lowercase"
    // the java way
    println(Utils().upperFirstAndLast(str))
    // the kotlin way
    println(str.upperFirstAndLast())

}


// the kotlin way
fun String.upperFirstAndLast(): String =
    capitalize().reversed().capitalize().reversed()


data class Car(val brand: String, val model: String)


fun labelMultiply(op1: Int, op2: Int, label: String = "The result is: ") =
        "$label${op1 * op2}"


fun printBrands(prefix: String = "My cars: ", vararg cars: Car) =
        cars.forEach { println(it.brand) }


class Employee(val name: String) {
    fun upperCaseName() = name.toUpperCase()
}
