package maxzonov.vkapi_sandbox.data.photos

import com.google.gson.annotations.SerializedName

data class PhotoSize(
        @SerializedName("type") val type: String,
        @SerializedName("url") val url: String,
        @SerializedName("width") val width: Int,
        @SerializedName("height") val height: Int
)