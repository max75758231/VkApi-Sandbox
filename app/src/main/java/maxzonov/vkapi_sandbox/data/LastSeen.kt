package maxzonov.vkapi_sandbox.data

import com.google.gson.annotations.SerializedName

data class LastSeen (
        @SerializedName("time") val time: Long,
        @SerializedName("platform") val platform: Int
)