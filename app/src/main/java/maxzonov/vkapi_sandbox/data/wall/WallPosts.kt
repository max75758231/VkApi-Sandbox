package maxzonov.vkapi_sandbox.data.wall

import com.google.gson.annotations.SerializedName
import maxzonov.vkapi_sandbox.data.groups.Group

data class WallPosts(
    @SerializedName("count") val count: Int,
    @SerializedName("items") val responseItems: ArrayList<WallPost>,
    @SerializedName("profiles") val profiles: ArrayList<WallProfile>,
    @SerializedName("groups") val groups: ArrayList<Group>
)