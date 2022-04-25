package fpinkotlin.chapter01


class Cafe03 {
    fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> {
        val cup = Coffee("java", 1.5)
        val charge = Charge(cc, cup.price)
        return Pair(cup, charge)
    }

    fun buyCoffees(cc: CreditCard, n: Int): Pair<List<Coffee>, Charge> {
        val (coffees, charges) = List(n) { buyCoffee(cc) }.unzip()
        return Pair(coffees, charges.reduce { a, b -> a.combine(b) })
    }
}
