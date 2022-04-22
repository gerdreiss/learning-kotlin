package com.jscriptive.learningkotlin.inheritance

fun main(args: Array<String>) {
    LaserPrinter("Brother 1234", 20).printModel()
}

/*open*/ abstract class Printer(val modelName: String) {

    open fun printModel() =
            println("The model name of this printer is $modelName")

    abstract fun price(): Double
}

open class LaserPrinter(modelName: String, ppm: Int) : Printer(modelName) {
    override fun price(): Double = 99.90
    // when there is no primary constructor
    //constructor(): super()

    // can't override this one
    final override fun printModel() =
            println("The model name of this laser printer is $modelName")
}

class SpecialLaserPrinter(modelName: String, ppm: Int) : LaserPrinter(modelName, ppm) {
    override fun price(): Double = 129.90

}

open class Something {
    constructor(p: String)
}

open class SomethingElse : Something("Else")
