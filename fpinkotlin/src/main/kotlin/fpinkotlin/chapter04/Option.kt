package fpinkotlin.chapter04

sealed class Option<out A>

data class Some<out A>(val value: A) : Option<A>()

object None : Option<Nothing>()

fun <A, B> Option<A>.map(f: (A) -> B): Option<B> =
    when (this) {
        is None -> None
        is Some -> Some(f(value))
    }

fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> =
    when (this) {
        is None -> None
        is Some -> f(value)
    }

fun <A> Option<A>.getOrElse(default: () -> A): A =
    when (this) {
        is None -> default()
        is Some -> value
    }

fun <A> Option<A>.orElse(ob: () -> Option<A>): Option<A> =
    when (this) {
        is None -> ob()
        is Some -> this
    }

fun <A> Option<A>.filter(f: (A) -> Boolean): Option<A> =
    when (this) {
        is None -> None
        is Some -> if (f(value)) this else None
    }

fun List<Double>.mean(): Option<Double> =
    if (this.isEmpty()) None
    else Some(this.sum() / this.size)

fun List<Double>.variance(): Option<Double> =
    mean().flatMap { m ->
        this.map { it - m }
            .map { it * it }
            .mean()
    }

fun <A, B, C> map2(maybeA: Option<A>, maybeB: Option<B>, f: (A, B) -> C): Option<C> =
    maybeA.flatMap { a ->
        maybeB.map { b ->
            f(a, b)
        }
    }

fun <A> List<Option<A>>.sequence(): Option<List<A>> =
    fold(Some(listOf())) { acc, maybeA ->
        acc.flatMap { maybeA.map { a -> it + a } }
    }

fun <A> List<A>.traverse(f: (A) -> Option<A>): Option<List<A>> =
    fold(Some(listOf())) { acc, a ->
        f(a).flatMap { acc.map { it + a } }
    }

fun main() {
    println(listOf(1, 2, 3).traverse<Int> { Some(it) })
}
