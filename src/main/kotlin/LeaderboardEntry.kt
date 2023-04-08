import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeaderboardEntry(
    @SerialName("puuid") val puuid: String? = null,
    val gameName: String? = null,
    val tagLine: String? = null,
    val leaderboardRank: Long,
    val rankedRating: Long,
    val numberOfWins: Long
)
