package parlib

class Par<A>(val get: A)

fun <A> unit(a: () -> A): Par<A> = Par(a())

fun <A> get(a: Par<A>): A = a.get
