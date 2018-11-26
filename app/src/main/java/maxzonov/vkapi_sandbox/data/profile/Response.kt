package maxzonov.vkapi_sandbox.data.profile

import com.google.gson.annotations.SerializedName

data class Response (
        @SerializedName("response") val profiles: ArrayList<Profile>
)