package fpinkotlin.chapter05

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

sealed class Stream<out A> {
    companion object {
        fun <A> empty(): Stream<A> = Empty

        fun <A> cons(head: () -> A, tail: () -> Stream<A>): Stream<A> {
            val h: A by lazy(head)
            val t: Stream<A> by lazy(tail)
            return Cons({ h }, { t })
        }

        fun <A> of(vararg xs: A): Stream<A> =
            if (xs.isEmpty()) empty()
            else cons({ xs[0] }, { of(*xs.sliceArray(1 until xs.size)) })
    }
}

data class Cons<out A>(val head: () -> A, val tail: () -> Stream<A>) : Stream<A>()

object Empty : Stream<Nothing>()

fun <A> Stream<A>.headOption(): Option<A> =
    when (this) {
        is Empty -> None
        is Cons -> Some(this.head())
    }

fun <A> Stream<A>.headOptionViaFoldRight(): Option<A> =
    foldRight(
        { None as Option<A> },
        { a, _ -> Some(a) }
    )

fun <A> Stream<A>.toListUnsafe(): List<A> =
    when (this) {
        is Empty -> listOf()
        is Cons -> listOf(head()) + tail().toListUnsafe()
    }

fun <A> Stream<A>.toList(): List<A> {
    fun go(rest: Stream<A>, acc: List<A>): List<A> =
        when (rest) {
            is Empty -> acc
            is Cons -> go(rest.tail(), acc + rest.head())
        }
    return go(this, listOf())
}

fun <A> Stream<A>.take(n: Int): Stream<A> =
    when (this) {
        is Empty -> Stream.empty()
        is Cons ->
            if (n > 0) Stream.cons(head) { tail().take(n - 1) }
            else Stream.empty()
    }

fun <A> Stream<A>.takeWhile(p: (A) -> Boolean): Stream<A> =
    when (this) {
        is Empty -> Stream.empty()
        is Cons ->
            if (p(head())) Stream.cons(head) { tail().takeWhile(p) }
            else Stream.empty()
    }

fun <A> Stream<A>.takeWhileViaFoldRight(p: (A) -> Boolean): Stream<A> =
    foldRight({ Stream.empty() }) { a, acc ->
        if (p(a)) Stream.cons({ a }, { acc().takeWhileViaFoldRight(p) })
        else Stream.empty()
    }

fun <A, B> Stream<A>.foldRight(
    z: () -> B,
    f: (A, () -> B) -> B
): B =
    when (this) {
        is Cons -> f(this.head()) {
            tail().foldRight(z, f)
        }
        is Empty -> z()
    }

fun <A> Stream<A>.exists(p: (A) -> Boolean): Boolean =
    foldRight({ false }) { a, b -> p(a) || b() }

fun <A> Stream<A>.forAll(p: (A) -> Boolean): Boolean =
    foldRight({ true }) { a, b -> p(a) && b() }

fun <A, B> Stream<A>.map(f: (A) -> B): Stream<B> =
    foldRight({ Stream.empty() }) { a, b ->
        Stream.cons({ f(a) }, { b() })
    }

fun <A> Stream<A>.filter(p: (A) -> Boolean): Stream<A> =
    foldRight({ Stream.empty() }) { a, b ->
        if (p(a)) Stream.cons({ a }, { b() })
        else b()
    }

fun <A> Stream<A>.append(s: Stream<A>): Stream<A> =
    foldRight({ s }) { a, b ->
        Stream.cons({ a }, { b() })
    }

fun <A, B> Stream<A>.flatMap(f: (A) -> Stream<B>): Stream<B> =
    foldRight({ Stream.empty() }) { a, b ->
        f(a).append(b())
    }



