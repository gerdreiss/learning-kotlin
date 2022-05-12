package demo

import org.apache.pulsar.client.api.Producer
import org.apache.pulsar.client.api.PulsarClient
import org.apache.pulsar.client.api.Schema

object Pulsar {
    private const val pulsarUrl = "pulsar://localhost:6650"
    private const val topic = "persistent://loans/demo/ns1/loanEvents"

    internal fun client(): PulsarClient =
        PulsarClient.builder().serviceUrl(pulsarUrl).build()

    internal fun producer(client: PulsarClient): Producer<String> =
        client.newProducer(Schema.STRING).topic(topic).create()
}
