package fpinkotlin

import arrow.core.Either
import arrow.core.Option
import arrow.core.continuations.either
import arrow.core.continuations.option
import arrow.core.getOrElse
import fpinkotlin.chapter02.abs
import fpinkotlin.chapter02.fac
import fpinkotlin.chapter02.factorial
import fpinkotlin.chapter02.factr
import fpinkotlin.chapter02.formatResult
import fpinkotlin.chapter04.map
import fpinkotlin.chapter04.mkAge
import java.nio.file.Files
import java.nio.file.Paths

suspend fun <A, B, C, D> options(
    oa: Option<A>,
    ob: Option<B>,
    oc: Option<C>,
    f: (A, B, C) -> D
): Option<D> =
    option {
        val a = oa.bind()
        val b = ob.bind()
        val c = oc.bind()
        f(a, b, c)
    }

suspend fun <A, B, C, D, E> eithers(
    eae: Either<E, A>,
    ebe: Either<E, B>,
    ece: Either<E, C>,
    f: (A, B, C) -> D
): Either<E, D> =
    either {
        val a = eae.bind()
        val b = ebe.bind()
        val c = ece.bind()
        f(a, b, c)
    }

suspend fun main(args: Array<String>) {
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

    mkAge(10).also { println(it) }
    mkAge(10).apply { println(this) }

    println("=".repeat(50))
    println(with(mkAge(10)) {
        map { it.copy(value = it.value + 2) }
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

    println("=".repeat(50))
    println(
        options(
            Option.fromNullable(1),
            Option.fromNullable(2),
            Option.fromNullable(3)
        ) { a, b, c -> a + b + c }
    )

    println("=".repeat(50))
    println(
        eithers(
            Either.fromNullable(1),
            Either.fromNullable(2),
            Either.fromNullable(3)
        ) { a, b, c -> a + b + c }
    )


}
