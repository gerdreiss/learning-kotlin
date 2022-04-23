package fpinkotlin

import arrow.core.Either
import arrow.core.Option
import arrow.core.getOrElse
import fpinkotlin.chapter02.abs
import fpinkotlin.chapter02.fac
import fpinkotlin.chapter02.factorial
import fpinkotlin.chapter02.factr
import fpinkotlin.chapter02.formatResult
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    Either
        .catch { Files.list(Paths.get(".")) }
        .fold(
            { println("Error: $it") },
            { paths ->
                val files = paths
                    .map { it.fileName.toString() }
                    .reduce { t, u -> "$t\n$u" }
                    .orElse(".")
                println(files)
            })

    println("=".repeat(50))

    Option
        .fromNullable(null as String?)
        .map { it.length }
        .getOrElse { 0 }
        .let { println(it) }

    println("=".repeat(50))
    println(fac(10))
    println(factr(10))

    println("=".repeat(50))
    println(formatResult("absolute value", -10) { n -> if (n < 0) -n else n })
    println(formatResult("absolute value", -10, ::abs))
    println(formatResult("factorial", 10, ::factorial))

}
