package maxzonov.vkapi_sandbox.data

import com.google.gson.annotations.SerializedName

data class Profile (
        @SerializedName("id") val id: String,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String,
        @SerializedName("sex") val sex: Int,
        @SerializedName("online") val online: Int,
        @SerializedName("home_town") val homeTown: String,
        @SerializedName("bdate") val birthDate: String,
        @SerializedName("photo_max") val photoCropped: String,
        @SerializedName("last_seen") val lastSeen: LastSeen
)