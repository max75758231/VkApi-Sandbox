package maxzonov.vkapi_sandbox.data.post

import com.google.gson.annotations.SerializedName

data class PostProfile(
    @SerializedName("id") val id: Long,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("is_closed") val isClosed: Boolean,
    @SerializedName("photo_50") val photoUrl: String,
    @SerializedName("online") val isOnline: Short
)