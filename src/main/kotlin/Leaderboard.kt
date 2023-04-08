import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Leaderboard(
    @SerialName("players") val entries: List<LeaderboardEntry>
)
