package maxzonov.vkapi_sandbox.data

import com.google.gson.annotations.SerializedName

data class Response (
        @SerializedName("response") val response: ArrayList<Profile>
)