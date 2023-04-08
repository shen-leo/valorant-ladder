import kotlinx.coroutines.runBlocking
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*

fun readApiKey(): String {
    val properties = Properties()
    val inputStream: InputStream = object {}.javaClass.classLoader.getResourceAsStream("config.properties")
        ?: throw FileNotFoundException("config.properties not found")
    properties.load(inputStream)
    return properties.getProperty("riotApiKey") ?: throw IllegalStateException("API key not found in config.properties")
}

fun outputLeaderboard(actId: String, size: String, startIndex: String) = runBlocking {
    val leaderboard = fetchLeaderboard(readApiKey(), actId, size, startIndex)

    // Print the leaderboard data with player names
    leaderboard.forEach { entry ->
        val playerName = entry.puuid?.let { getPlayerNameByPuuid(readApiKey(), it) }
        println("${entry.leaderboardRank}. ${playerName ?: entry.puuid} - ${entry.rankedRating}")
    }
}

fun main() = runBlocking {
    val actId = "97b6e739-44cc-ffa7-49ad-398ba502ceb0"
    val size = "10"
    val startIndex = "0"

    outputLeaderboard(actId, size, startIndex)
}
