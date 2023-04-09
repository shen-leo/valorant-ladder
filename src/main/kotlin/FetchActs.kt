
import org.json.JSONObject
import java.io.File

fun getActId(jsonFilePath: String, episodeName: String, actName: String): String? {
    val jsonStr = File(jsonFilePath).readText()
    val json = JSONObject(jsonStr)
    val acts = json.getJSONArray("acts")
    var actId: String? = null
    for (i in 0 until acts.length()) {
        val act = acts.getJSONObject(i)
        if (act.getString("name") == actName && act.getString("type") == "act") {
            val parentId = act.getString("parentId")
            val parentEpisode = acts.filterIsInstance<JSONObject>()
                .find { it.getString("id") == parentId && it.getString("type") == "episode" }
            if (parentEpisode != null && parentEpisode.getString("name") == episodeName) {
                actId = act.getString("id")
                break
            }
        }
    }
    return actId
}
