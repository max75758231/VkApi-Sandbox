package maxzonov.vkapi_sandbox.data.photos

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("count") val commentsNumber: Int
)