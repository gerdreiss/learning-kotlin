package fpinkotlin.chapter02

import arrow.core.andThen

object Exercises {

    val <T> List<T>.head: T
        get() = first()

    val <T> List<T>.tail: List<T>
        get() = drop(1)

    fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean =
        aa.isEmpty() || aa.tail.isEmpty() || order(aa.head, aa.tail.head) && isSorted(
            aa.tail,
            order
        )

    fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C = { a -> { b -> f(a, b) } }

    fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C = { a, b -> f(a)(b) }

    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = { a -> f(g(a)) } // g.andThen(f)
}


fun main(args: Array<String>) {
    println(Exercises.isSorted(listOf(1, 2, 3, 4, 5)) { a, b -> a < b })
    println(Exercises.isSorted(listOf(1, 2, 3, 4, 5)) { a, b -> a > b })

    fun <A, B, C> f(a: A, b: B): C = TODO()
    fun <C, D> g(c: C): D = TODO()

    fun add(a: Int, b: Int): Int = a + b
    fun tenfold(a: Int): Int = a * 10

    ::add.andThen(::tenfold)

    val curried = Exercises.curry<Int, Int, Int>(::f)
    val uncurried = Exercises.uncurry(curried)
}
