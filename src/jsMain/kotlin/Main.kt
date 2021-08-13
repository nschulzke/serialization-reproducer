import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlinx.serialization.json.Json

interface Interface

@Serializable
sealed class Subtype : Interface {
    data class SubSubType(val value: String)
}

fun main() {
    val module = SerializersModule {
        polymorphic(Interface::class) {
            subclass(Subtype::class)
        }
    }
    val json = Json {
        serializersModule = module
    }

    val value1 = Subtype.SubSubType("test")
    val string = json.encodeToString(value1)
    val value2 = json.decodeFromString<Interface>(string)

    println(value2.toString())
}
