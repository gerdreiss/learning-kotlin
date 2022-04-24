package fpinkotlin.chapter02

fun abs(n: Int): Int = if (n < 0) -n else n

fun factorial(n: Int): Int {
    tailrec fun go(n: Int, acc: Int): Int =
        if (n <= 0) acc
        else go(n - 1, n * acc)

    return go(n, 1)
}

fun formatAbs(x: Int): String =
    "The absolute value of %d is %d".format(x, abs(x))

fun formatFactorial(n: Int): String =
    "The factorial of %d is %d".format(n, factorial(n))

fun formatResult(name: String, n: Int, f: (Int) -> Int) =
    "The %s of %d is %d".format(name, n, f(n))
