package com.jscriptive.learningkotlin.accessmodifiers

// define top level constant
val CONSTANT = 1000

fun main(args: Array<String>) {
    println(CONSTANT)

    val capri1 = Vehicle("Ford", "Capri", 1985)
    val capri2 = Vehicle("Ford", "Capri", 1985)
    val capri3 = capri1.copy(year = 1980)
    val mondeo = Vehicle("Ford", "Mondeo", 2016)
    println(capri1)
    println(capri2)
    println(capri3)
    println(mondeo)
    println(capri1 == capri2)
    println(capri2 == capri3)
    println(capri1 == mondeo)

    println(Employee("John", "Doe"))
    println(Employee("John", "Doe", false))
    println(Employee2("John", "Doe", false))
    println(Employee3("John", "Doe", false))
}

data class Vehicle(val brand: String, val model: String, val year: Int)

private class Employee /*protected|private|internal constructor*/(val firstName: String, val lastName: String) {
    // the stuff below is done automatically by kotlin
    //val firstName: String = firstName
    //val lastName: String = lastName
    //init {
    //    this.firstName = firstName
    //    this.lastName = lastName
    //}

    var fullTime: Boolean = true

    constructor(firstName: String, lastName: String, fullTime: Boolean) : this(firstName, lastName) {
        this.fullTime = fullTime
    }

    override fun toString(): String {
        return "Employee(firstName='$firstName', lastName='$lastName', fullTime=$fullTime)"
    }
}

private class Employee2(val firstName: String, val lastName: String, var fullTime: Boolean = true) {
    override fun toString(): String {
        return "Employee2(firstName='$firstName', lastName='$lastName', fullTime=$fullTime)"
    }
}

private class Employee3 {
    val firstName: String
    val lastName: String

    // custom getter
    private var fullTime: Boolean = true
        get() {
            println("running the custom getter")
            return field
        }
        set(value) {
            println("running the custom setter")
            field = value
        }

    constructor(firstName: String, lastName: String, fullTime: Boolean) {
        this.firstName = firstName
        this.lastName = lastName
        this.fullTime = fullTime
    }

    override fun toString(): String {
        return "Employee3(firstName='$firstName', lastName='$lastName', fullTime=$fullTime)"
    }

}

// class are public and final by default!

