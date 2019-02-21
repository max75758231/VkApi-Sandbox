package maxzonov.vkapi_sandbox.data.post

import com.google.gson.annotations.SerializedName

data class PostVideo(
    @SerializedName("photo_130") val imagePreviewUrl130: String,
    @SerializedName("photo_320") val imagePreviewUrl320: String
)