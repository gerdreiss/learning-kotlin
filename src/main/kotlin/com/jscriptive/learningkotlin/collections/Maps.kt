package com.jscriptive.learningkotlin.collections


fun main(args: Array<String>) {
    val immutableMap = mapOf(
            1 to "Ford",
            2 to "Infiniti",
            3 to "Honda"
    )

    println(immutableMap)
    println(immutableMap.javaClass)

    val mutableMap = mutableMapOf(
            1 to "Ford",
            2 to "Honda",
            3 to "Nissan"
    )
    mutableMap.put(4, "Corvette")

    println(mutableMap)
    println(mutableMap.javaClass)

    for (entry in mutableMap) {
        println(entry.key)
        println(entry.value)
    }
    // or
    for ((k, v) in mutableMap) {
        println(k)
        println(v)
    }

    //val car = Car("Ford","Capri",1980)
    //val (brand, model, year) = car
    //println("${year}er $brand $model")


    val vehicle = Vehicle("Ford", "Capri", 1980)
    val (brand, model, year) = vehicle
    println("${year}er $brand $model")
}

class Car(val brand: String, val model: String, val year: Int) {
    operator fun component1() = brand
    operator fun component2() = model
    operator fun component3() = year
}

data class Vehicle(val brand: String, val model: String, val year: Int)