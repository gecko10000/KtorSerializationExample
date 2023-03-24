package gecko10000.kse

import io.ktor.util.collections.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


@Serializable
data class RecursiveObject(
    val name: String,
    @Serializable(with = ROStringSerializer::class)
    val parent: RecursiveObject? = null,
    val children: MutableSet<@Serializable(with = ROStringSerializer::class) RecursiveObject> = ConcurrentSet()
)

class ROStringSerializer : KSerializer<RecursiveObject> {
    override val descriptor = PrimitiveSerialDescriptor("RecursiveObject", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: RecursiveObject) = encoder.encodeString(value.name)

    // unused
    override fun deserialize(decoder: Decoder): RecursiveObject {
        TODO("Not yet implemented")
    }
}
