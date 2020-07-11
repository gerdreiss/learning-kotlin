package com.jscriptive.learningkotlin.coursera

fun m(i: Int): Int {
    print("m$i ")
    return i
}

fun f(i: Int): Boolean {
    print("f$i ")
    return i % 2 == 0
}

fun mySequence() = sequence {
    println("yield one element")
    yield(1)
    println("yield a range")
    yieldAll(3..5)
    println("yield a list")
    yieldAll(listOf(7, 9))
}


fun fibonacci(): Sequence<Int> = sequence {
    var a = 0
    var b = 1
    var aux = 0
    // this sequence is infinite
    while (true) {
        yield(a)
        aux = b
        b += a
        a = aux
    }
}


fun main() {
    val list = listOf(1, 2, 3, 4)
    list.map(::m).filter(::f)  //m1 m2 m3 m4 f1 f2 f3 f4
    println()
    println()
    println()
    list.asSequence().map(::m).filter(::f).toList() // m1 f1 m2 f2 m3 f3 m4 f4
    println()
    println()
    list.asSequence().filter(::f).map(::m).toList() // f1 f2 m2 f3 f4 m4
    println()
    println()

    println(mySequence()
        .map { it * it }
        .filter { it > 10 }
        .take(1))
    println()
    println()
    println()

    println(fibonacci().take(10).toList())
}
