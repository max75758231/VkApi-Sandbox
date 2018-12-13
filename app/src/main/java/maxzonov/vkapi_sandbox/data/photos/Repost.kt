package maxzonov.vkapi_sandbox.data.photos

import com.google.gson.annotations.SerializedName

data class Repost(
    @SerializedName("count") val repostsNumber: Int
)