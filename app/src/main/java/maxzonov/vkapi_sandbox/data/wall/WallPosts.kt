package maxzonov.vkapi_sandbox.data.wall

import com.google.gson.annotations.SerializedName

data class WallPosts(
    @SerializedName("count") val count: Int,
    @SerializedName("items") val responseItems: ArrayList<WallPost>,
    @SerializedName("profiles") val profiles: ArrayList<WallProfile>
)