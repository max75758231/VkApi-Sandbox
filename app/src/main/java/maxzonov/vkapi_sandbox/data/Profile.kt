package maxzonov.vkapi_sandbox.data

import com.google.gson.annotations.SerializedName

data class Profile (
        @SerializedName("id") val id: String,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String
)