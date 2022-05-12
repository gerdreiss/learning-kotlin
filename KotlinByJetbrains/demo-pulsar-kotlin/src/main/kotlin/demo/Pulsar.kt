package demo

import org.apache.pulsar.client.api.Consumer
import org.apache.pulsar.client.api.Producer
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema
import org.apache.pulsar.client.api.SubscriptionType

object Pulsar {
    private const val pulsarUrl = "pulsar://localhost:6650"
    private const val topic = "persistent://loans/demo/loanEvents"

    internal fun client(): PulsarClient =
        PulsarClient.builder().serviceUrl(pulsarUrl).build()

    internal fun producer(client: PulsarClient): Producer<String> =
        client.newProducer(Schema.STRING).topic(topic).create()

    internal fun consumer(client: PulsarClient, name: String): Consumer<String> =
        client
            .newConsumer(Schema.STRING)
            .consumerName(name)
            .subscriptionName("LoanEvents")
            .subscriptionType(SubscriptionType.Shared)
            .topic(topic)
            .subscribe()
}
