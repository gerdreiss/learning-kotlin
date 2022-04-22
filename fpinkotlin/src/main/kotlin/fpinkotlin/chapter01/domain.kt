package fpinkotlin.chapter01

data class Coffee(val name: String, val price: Double)

class CreditCard(val number: String) {
    fun charge(price: Double) {
        // charge the credit card
    }
}

class Payments {
    fun charge(cc: CreditCard, amount: Double): Boolean {
        return true
    }
}

class Charge(val cc: CreditCard, val amount: Double) {
    fun combine(other: Charge): Charge {
        if (cc == other.cc) return Charge(cc, amount + other.amount)
        else throw IllegalArgumentException("Cannot combine charges to different cards")
    }
}

fun List<Charge>.coalesce(): List<Charge> =
    this.groupBy { it.cc }
        .values
        .map { it.reduce { a, b -> a.combine(b) } }
