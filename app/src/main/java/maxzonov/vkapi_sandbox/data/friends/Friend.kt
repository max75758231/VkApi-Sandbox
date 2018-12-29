package maxzonov.vkapi_sandbox.data.friends

import com.google.gson.annotations.SerializedName

data class Friend(
    @SerializedName("id") val id: Long,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("online") val isOnline: Short,
    @SerializedName("photo_100") val photoUrl: String
)