package fpinkotlin.chapter01


class Cafe01 {
    fun buyCoffee(cc: CreditCard): Coffee {
        val cup = Coffee("java", 1.5)
        cc.charge(cup.price) // <-- side effect!!!
        return cup
    }
}
