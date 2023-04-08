import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeaderboardResponse(
    @SerialName("players") val entries: List<LeaderboardEntry>
)
