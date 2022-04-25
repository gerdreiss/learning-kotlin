package parselib

import arrow.core.Either

object ParseError

interface Parser<A>

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

    fun <A> listOfN(n: Int, p: Parser<A>): Parser<List<A>>

    //fun <A> many(pa: Parser<A>): Parser<List<A>>
    fun <A> Parser<A>.many(): Parser<List<A>>

    // fun <A, B> map(pa: Parser<A>, f: (A) -> B): Parser<B>
    fun <A, B> Parser<A>.map(f: (A) -> B): Parser<B>

    fun <A> succeed(a: A): Parser<A> = string("").map { a }

    // run(slice(('a' or 'b').many()), "aaba") == Right("aaba")
    fun <A> slice(p: Parser<A>): Parser<String>

    infix fun <A, B> Parser<A>.product(pb: Parser<B>): Parser<Pair<A, B>>

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