package fpinkotlin.chapter10

import arrow.core.None
import arrow.core.Option
import arrow.core.compose

object Monoids {

    interface Monoid<A> {
        val empty: A
        fun combine(a: A, b: A): A
    }

    val stringMonoid = object : Monoid<String> {
        override val empty: String = ""
        override fun combine(a: String, b: String): String = a + b
    }

    fun <A> listMonoid(): Monoid<List<A>> = object : Monoid<List<A>> {
        override val empty: List<A> = emptyList()
        override fun combine(a: List<A>, b: List<A>): List<A> = a + b
    }

    fun intAddition(): Monoid<Int> = object : Monoid<Int> {
        override val empty: Int = 0
        override fun combine(a: Int, b: Int): Int = a + b
    }

    fun intMultiplication(): Monoid<Int> = object : Monoid<Int> {
        override val empty: Int = 1
        override fun combine(a: Int, b: Int): Int = a * b
    }

    fun booleanOr(): Monoid<Boolean> = object : Monoid<Boolean> {
        override val empty: Boolean = false
        override fun combine(a: Boolean, b: Boolean): Boolean = a || b
    }

    fun booleanAnd(): Monoid<Boolean> = object : Monoid<Boolean> {
        override val empty: Boolean = true
        override fun combine(a: Boolean, b: Boolean): Boolean = a && b
    }

    fun <A> optionMonoid(M: Monoid<A>) = object : Monoid<Option<A>> {
        override val empty: Option<A> = None
        override fun combine(a: Option<A>, b: Option<A>): Option<A> =
            a.flatMap { _a ->
                b.map { _b ->
                    M.combine(_a, _b)
                }
            }
    }

    fun <A> dual(M: Monoid<A>): Monoid<A> = object : Monoid<A> {
        override val empty: A
            get() = M.empty

        override fun combine(a: A, b: A): A = M.combine(a, b)
    }

    fun <A> endoMonoid(): Monoid<Endo<A>> = object : Monoid<Endo<A>> {
        override val empty: Endo<A>
            get() = { a -> a }

        override fun combine(a: Endo<A>, b: Endo<A>): Endo<A> =
            { _a -> a(b(_a)) }
    }

    fun <A> endoMonoidComposed(): Monoid<(A) -> A> = object : Monoid<(A) -> A> {
        override val empty: (A) -> A
            get() = { it }

        override fun combine(a: (A) -> A, b: (A) -> A): (A) -> A =
            a compose b
    }

    fun <A, B> foldMap(la: List<A>, M: Monoid<B>, f: (A) -> B): B =
        la.foldRight(M.empty) { a, b -> M.combine(f(a), b) }
}

typealias Endo<A> = (A) -> A