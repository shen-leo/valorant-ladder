import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

suspend fun createLeaderboard(actId: String, size: String, startIndex: String): Map<String, LeaderboardInfo> = runBlocking {
    val leaderboard = fetchLeaderboard(readApiKey(), actId, size, startIndex)

    val leaderboardInfo = mutableMapOf<String, LeaderboardInfo>()

    leaderboard.map { entry ->
        async {
            val playerName = entry.puuid?.let { getPlayerNameByPuuid(readApiKey(), it) }
            entry.puuid to LeaderboardInfo(entry.leaderboardRank, playerName ?: "", entry.rankedRating)
        }
    }.awaitAll().forEach { (puuid, info) ->
        leaderboardInfo[puuid ?: ""] = info
    }

    leaderboardInfo.toMap()
}

fun main() = runBlocking {
    val actId = "97b6e739-44cc-ffa7-49ad-398ba502ceb0"
    val size = "10"
    val startIndex = "0"

    val leaderboardInfo = createLeaderboard(actId, size, startIndex)

    leaderboardInfo.forEach { (_, info) ->
        println("${info.rank}. ${info.name} - ${info.rankedRating}")
    }
}
