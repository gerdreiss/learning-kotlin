package fpinkotlin.chapter02

fun fac(n: Int): Int {
    tailrec fun go(n: Int, acc: Int): Int =
        if (n <= 0) acc
        else go(n - 1, n * acc)

    return go(n, 1)
}

tailrec fun factr(n: Int, acc: Int = 1): Int =
    if (n <= 0) acc
    else factr(n - 1, n * acc)

fun fib(n: Int): Int =
    if (n <= 2) 1
    else fib(n - 1) + fib(n - 2)

//int fib(int n, int a = 0, int b = 1)
//{
//    if (n == 0)
//        return a;
//    if (n == 1)
//        return b;
//    return fib(n - 1, b, a + b);
//}

fun fibtr(n: Int): Int {
    tailrec fun go(n: Int, a: Int = 0, b: Int = 1): Int =
        when (n) {
            0 -> a
            1 -> b
            else -> go(n - 1, b, a + b)
        }

    return go(n)
}
