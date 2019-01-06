package maxzonov.vkapi_sandbox.data.wall

import com.google.gson.annotations.SerializedName

data class Attachment(
    @SerializedName("type") val type: String,
    @SerializedName("photo") val photo: WallPhoto
)