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
