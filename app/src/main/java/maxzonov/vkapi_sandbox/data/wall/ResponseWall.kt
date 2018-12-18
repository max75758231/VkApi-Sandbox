package maxzonov.vkapi_sandbox.data.wall

import com.google.gson.annotations.SerializedName

data class ResponseWall(
    @SerializedName("response") val wallPosts: WallPosts
)