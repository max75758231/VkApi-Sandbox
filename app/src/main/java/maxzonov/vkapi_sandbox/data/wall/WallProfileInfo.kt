package maxzonov.vkapi_sandbox.data.wall

import com.google.gson.annotations.SerializedName

data class WallProfileInfo (
    @SerializedName("first_name") val id: Long,
    @SerializedName("last_name") val firstName: String,
    @SerializedName("photo_50") val lastName: String,
    @SerializedName("online") val online: Short
)