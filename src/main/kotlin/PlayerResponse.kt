import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponse(
    @SerialName("gameName") val name: String,
    @SerialName("tagLine") val tag: String
)
