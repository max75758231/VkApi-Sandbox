package maxzonov.vkapi_sandbox.data.post

import com.google.gson.annotations.SerializedName

data class PostAttachment(
    @SerializedName("type") val type: String,
    @SerializedName("photo") val photo: PostPhoto
)