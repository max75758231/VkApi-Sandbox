package maxzonov.vkapi_sandbox.data.photos

import com.google.gson.annotations.SerializedName

data class Like (
    @SerializedName("count") val likesNumber: Int
)