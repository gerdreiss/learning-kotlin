package fpinkotlin.chapter01


class Cafe02 {
    fun buyCoffee(cc: CreditCard, p: Payments): Coffee {
        val cup = Coffee("java", 1.5)
        cc.charge(cup.price)
        return cup
    }
}