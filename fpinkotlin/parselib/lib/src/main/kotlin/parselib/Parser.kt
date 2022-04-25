package parselib

import arrow.core.Either

object ParseError

interface Parser<A>

infix fun <T> T.cons(ts: List<T>): List<T> = listOf(this) + ts

interface Parsers<PE> {

    fun <A> run(p: Parser<A>, input: String): Either<PE, A>

    // run(char(c), c.toString()) == Right(c)
    fun char(c: Char): Parser<Char> = string(c.toString()).map { it.first() }

    // run(string(s), s) == Right(s)
    fun string(s: String): Parser<String>

    // run("abra" or "cadabra", "abra") == Right("abra")
    fun <A> or(p1: Parser<A>, p2: Parser<A>): Parser<A>

    infix fun String.or(other: String): Parser<String> =
        or(string(this), string(other))

    fun <A> listOfN(n: Int, pa: Parser<A>): Parser<List<A>> =
        if (n == 0) succeed(emptyList())
        else map2(pa, { listOfN(n - 1, pa) }) { a, la -> listOf(a) + la }

    //fun <A> many(pa: Parser<A>): Parser<List<A>>
    fun <A> Parser<A>.many(): Parser<List<A>> =
        map2(this, { this.many() }) { a, la -> listOf(a) + la }

    // fun <A, B> map(pa: Parser<A>, f: (A) -> B): Parser<B>
    fun <A, B> Parser<A>.map(f: (A) -> B): Parser<B>

    fun <A> succeed(a: A): Parser<A> = string("").map { a }

    // run(slice(('a' or 'b').many()), "aaba") == Right("aaba")
    fun <A> slice(p: Parser<A>): Parser<String>

    infix fun <A, B> Parser<A>.product(pb: Parser<B>): Parser<Pair<A, B>>

    fun <A, B, C> map2(
        pa: Parser<A>,
        pb: () -> Parser<B>,
        f: (A, B) -> C
    ): Parser<C> =
        pa.product(pb()).map { (a, b) -> f(a, b) }

    fun <A> many1(p: Parser<A>): Parser<List<A>> =
        p.many()
}

//abstract class Laws : Parsers<ParseError> {
//    private fun <A> equal(
//        p1: Parser<A>,
//        p2: Parser<A>,
//        i: Gen<String>
//    ): Prop =
//        forAll(i) { s -> run(p1, s) == run(p2, s) }
//
//    fun <A> mapLaw(p: Parser<A>, i: Gen<String>): Prop =
//        equal(p, p.map { a -> a }, i)
//}
