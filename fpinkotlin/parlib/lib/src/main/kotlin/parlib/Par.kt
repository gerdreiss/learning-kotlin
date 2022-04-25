package parlib

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

//class Par<A>(val get: A)
//fun <A> unit(a: A): Par<A> = Par(a)
//fun <A, B, C> map2(a: Par<A>, b: Par<B>, f: (A, B) -> C): Par<C> = TODO()
//fun <A> fork(a: () -> Par<A>): Par<A> = TODO()

typealias Par<A> = (ExecutorService) -> Future<A>

fun <A> run(es: ExecutorService, a: Par<A>): Future<A> = a(es)

object Pars {

    data class UnitFuture<A>(val a: A) : Future<A> {
        override fun get(): A = a
        override fun get(timeout: Long, timeUnit: TimeUnit): A = a
        override fun cancel(evenIfRunning: Boolean): Boolean = false
        override fun isDone(): Boolean = true
        override fun isCancelled(): Boolean = false
    }

    data class TimedMap2Future<A, B, C>(
        val fa: Future<A>,
        val fb: Future<B>,
        val f: (A, B) -> C
    ) : Future<C> {

        override fun isDone(): Boolean = TODO("Unused")
        override fun get(): C = TODO("Unused")

        override fun get(to: Long, tu: TimeUnit): C {
            val timeoutMillis = TimeUnit.MILLISECONDS.convert(to, tu)
            val start = System.currentTimeMillis()
            val a = fa.get(to, tu)
            val duration = System.currentTimeMillis() - start
            val remainder = timeoutMillis - duration
            val b = fb.get(remainder, TimeUnit.MILLISECONDS)
            return f(a, b)
        }

        override fun cancel(evenIfRunning: Boolean): Boolean = TODO("Unused")
        override fun isCancelled(): Boolean = TODO("Unused")
    }

    fun <A> unit(a: A): Par<A> =
        { _: ExecutorService -> UnitFuture(a) }

    fun <A> lazyUnit(a: () -> A): Par<A> = fork { unit(a()) }

    fun <A, B> asyncF(f: (A) -> B): (A) -> Par<B> = { a: A -> lazyUnit { f(a) } }

    fun <A, B, C> map2(
        a: Par<A>,
        b: Par<B>,
        f: (A, B) -> C
    ): Par<C> =
        { es: ExecutorService ->
            val fa: Future<A> = a(es)
            val fb: Future<B> = b(es)
            TimedMap2Future(fa, fb, f)
        }

    fun <A> fork(a: () -> Par<A>): Par<A> =
        { es: ExecutorService ->
            es.submit(Callable { a()(es).get() })
        }

    fun sortPar(parList: Par<List<Int>>): Par<List<Int>> =
        map2(parList, unit(Unit)) { a, _ -> a.sorted() }

    fun <A, B> parMap(ps: List<A>, f: (A) -> B): Par<List<B>> = TODO()
}

