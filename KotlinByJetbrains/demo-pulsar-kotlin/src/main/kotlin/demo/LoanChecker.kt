package demo

class LoanChecker {
    fun check(loan: Loan): LoanStatus =
        when (loan.amount.cents > 500_000) {
            true -> LoanStatus.Declined("Loan amount is too high")
            false -> LoanStatus.Approved
        }
}

sealed class LoanStatus {
    object Approved : LoanStatus()
    class Declined(reason: String) : LoanStatus()
}


fun main() {
    Pulsar.client().use { client ->
        val consumer = Pulsar.consumer(client, "LoanChecker")
        while (true) {
            val message = consumer.receive()
            println(LoanApplied.fromJson(message.value))
            consumer.acknowledge(message)
        }
    }
}
