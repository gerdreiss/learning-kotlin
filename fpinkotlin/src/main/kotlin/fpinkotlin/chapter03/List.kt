package fpinkotlin.chapter03

sealed class List<out A> {
    companion object {
        fun <A> of(vararg `as`: A): List<A> = `as`.foldRight(Nil as List<A>, ::Cons)
    }
}

object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()

fun <A> List<A>.head(): A = when (this) {
    is Nil -> throw IllegalStateException("head called on an empty list")
    is Cons -> head
}

fun <A> List<A>.init(): List<A> = when (this) {
    is Nil -> throw IllegalStateException("init called on an empty list")
    is Cons -> when (tail) {
        is Nil -> Cons(head, Nil)
        is Cons -> Cons(head, tail.init())
    }
}

fun <A> List<A>.tail(): List<A> = when (this) {
    is Nil -> throw IllegalStateException("tail of empty list")
    is Cons -> tail
}

fun <A> List<A>.prepend(a: A): List<A> = Cons(a, this)

fun <A> List<A>.append(`as`: List<A>): List<A> =
    when (this) {
        is Nil -> `as`
        is Cons -> Cons(head, tail.append(`as`))
    }

fun <A> List<A>.drop(n: Int): List<A> =
    when (this) {
        is Nil ->
            if (n > 0) throw IndexOutOfBoundsException("dropping more elements than present")
            else this
        is Cons -> tail.drop(n - 1)
    }

fun <A> List<A>.dropWhile(p: (A) -> Boolean): List<A> =
    when (this) {
        is Nil -> this
        is Cons -> if (p(head)) tail.dropWhile(p) else this
    }

fun main(args: Array<String>) {
    val nums = List.of(1, 2, 3, 4, 5)
    println(nums)
    println(nums.prepend(0))
    println(nums.append(List.of(6, 7, 8)))
    println(nums.drop(5))
    // println(nums.drop(15)) throws an IndexOutOfBoundsException
    println(nums.dropWhile { it < 3 })
    println(nums.init())
    println(List.of(1).init())
}
