package demo

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jetbrains.annotations.TestOnly
import java.util.*
import kotlin.random.Random

@Serializable
@JvmInline
value class LoanId(@Serializable(with = UUIDSerializer::class) val value: UUID = UUID.randomUUID())

@Serializable
@JvmInline
value class UserId(@Serializable(with = UUIDSerializer::class) val value: UUID = UUID.randomUUID())

@Serializable
data class Amount(
    val cents: Int,
    @Serializable(with = CurrencySerializer::class)
    val currency: Currency
) {
    companion object {
        @TestOnly
        fun random() = Amount(
            Random.nextInt(100_000_000),
            Currency.getAvailableCurrencies().random()
        )
    }
}

@Serializable
data class Loan(val loadId: LoanId, val userId: UserId, val amount: Amount) {
    companion object {
        @TestOnly
        fun random(): Loan = Loan(LoanId(), UserId(), Amount.random())
    }
}

object CurrencySerializer : KSerializer<Currency> {
    override val descriptor = PrimitiveSerialDescriptor("CurrencySerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Currency =
        Currency.getInstance(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Currency) =
        encoder.encodeString(value.currencyCode)
}

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUIDSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID =
        UUID.fromString(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: UUID) =
        encoder.encodeString(value.toString())
}
