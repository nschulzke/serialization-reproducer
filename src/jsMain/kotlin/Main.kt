import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.json.Json

interface Interface

@Serializable
sealed class Subtype : Interface

fun main() {
    val module = SerializersModule {
        polymorphic(Interface::class) {
            subclass(Subtype::class, Subtype.serializer())
        }
    }
    val json = Json {
        serializersModule = module
    }
}
