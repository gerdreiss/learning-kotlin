package com.jscriptive.learningkotlin.collections


fun main(args: Array<String>) {

    val ints = 1..1000000
    // lazy evaluation, like streams in Java
    val filtered = ints.asSequence()
            .filter { it % 1000 == 1 }
               .map { "got $it"      }
               .map { it.reversed()  }
               .map { it.take(2)  }
            .toSet()
    println(filtered)
}