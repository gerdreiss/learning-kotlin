package fpinkotlin.chapter10

import arrow.typeclasses.Monoid

sealed class WC

data class Stub(val chars: String) : WC()
data class Part(val ls: String, val words: Int, val rs: String) : WC()

fun wcMonoid(): Monoid<WC> = object : Monoid<WC> {
    override fun empty(): WC = Stub("")

    override fun WC.combine(b: WC): WC =
        when (this) {
            is Stub -> when (b) {
                is Stub -> Stub(this.chars + b.chars)
                is Part -> Part(this.chars + b.words, b.words, b.rs)
            }
            is Part -> when (b) {
                is Stub -> Part(this.ls, this.words, this.rs + b.chars)
                is Part -> Part(this.ls, this.words + b.words, this.rs + b.rs)
            }
        }
}
