package demo

import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    Pulsar.client().use { client ->
        Pulsar.producer(client)
            .send(Json.encodeToString(LoanApplied.serializer(), LoanApplied(Loan.random())))
    }
}
