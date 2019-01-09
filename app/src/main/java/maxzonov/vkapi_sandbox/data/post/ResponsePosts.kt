package maxzonov.vkapi_sandbox.data.post

import com.google.gson.annotations.SerializedName

data class ResponsePosts(
    @SerializedName("response") val posts: Posts
)