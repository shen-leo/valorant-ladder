import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

suspend fun fetchLeaderboard(apiKey: String, actId: String, size: String, startIndex: String): List<LeaderboardEntry> {
    val apiUrl = "https://na.api.riotgames.com/val/ranked/v1/leaderboards/by-act/$actId?size=$size&startIndex=$startIndex"

    val json = Json { ignoreUnknownKeys = true }

    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        defaultRequest {
            header("X-Riot-Token", apiKey)
        }
    }

    val response: HttpResponse = client.get(apiUrl)
    val responseBody = response.readText()
    val leaderboard = json.decodeFromString(Leaderboard.serializer(), responseBody)
    client.close()
    return leaderboard.entries
}
