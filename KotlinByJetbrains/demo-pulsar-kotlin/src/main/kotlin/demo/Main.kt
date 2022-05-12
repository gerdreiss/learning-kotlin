import demo.Loan
import demo.LoanApplied
import demo.Pulsar
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    Pulsar.client().use { client ->
        val producer = Pulsar.producer(client)

        val event = LoanApplied(Loan.random())

        producer.send(Json.encodeToString(LoanApplied.serializer(), event))
    }
}
