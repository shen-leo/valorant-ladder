
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json


suspend fun getPlayerNameByPuuid(apiKey: String, puuid: String): String? {
    val apiUrl = "https://americas.api.riotgames.com/riot/account/v1/accounts/by-puuid/$puuid"

    val json = Json { ignoreUnknownKeys = true }

    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        defaultRequest {
            header("X-Riot-Token", apiKey)
        }
    }

    val playerName: Player? = try {
        client.get(apiUrl)
    } catch (e: Exception) {
        null
    }

    client.close()

    return playerName?.name
}
