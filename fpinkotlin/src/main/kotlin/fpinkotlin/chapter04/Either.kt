package fpinkotlin.chapter04

sealed class Either<out E, out A>
data class Left<out E>(val value: E) : Either<E, Nothing>()
data class Right<out A>(val value: A) : Either<Nothing, A>()

fun <E, A, B> Either<E, A>.map(f: (A) -> B): Either<E, B> =
    flatMap { a -> Right(f(a)) }

fun <E, A, B> Either<E, A>.flatMap(f: (A) -> Either<E, B>): Either<E, B> =
    when (this) {
        is Left -> this
        is Right -> f(value)
    }

fun <E, A> List<Either<E, A>>.sequence(): Either<E, List<A>> =
    fold(Right(listOf())) { acc, maybeA ->
        acc.flatMap { maybeA.map { a -> it + a } }
    }

fun <E, A> List<A>.traverse(f: (A) -> Either<E, A>): Either<E, List<A>> =
    fold(Right(listOf())) { acc, a ->
        f(a).flatMap { acc.map { it + a } }
    }

fun <A, B, C, E> map2(
    maybeA: Either<E, A>,
    maybeB: Either<E, B>,
    f: (A, B) -> C
): Either<List<E>, C> =
    when(maybeA) {
        is Left -> when(maybeB) {
            is Left -> Left(listOf(maybeA.value, maybeB.value))
            is Right -> Left(listOf(maybeA.value))
        }
        is Right -> when(maybeB) {
            is Left -> Left(listOf(maybeB.value))
            is Right -> Right(f(maybeA.value, maybeB.value))
        }
    }

data class Name(val value: String)
data class Age(val value: Int)
data class Person(val name: Name, val age: Age)

fun mkName(name: String): Either<String, Name> =
    if (name.isBlank()) Left("Name is empty.")
    else Right(Name(name))

fun mkAge(age: Int): Either<String, Age> =
    if (age < 0) Left("Age is out of range.")
    else Right(Age(age))

fun mkPerson(name: String, age: Int): Either<List<String>, Person> =
    map2(mkName(name), mkAge(age)) { n, a -> Person(n, a) }


fun main() {
    println(listOf(1, 2, 3).traverse<String, Int> { Right(it) })
    println(mkPerson("", -1))
}
