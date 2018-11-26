package maxzonov.vkapi_sandbox.data.photos

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("data") val photoDate: String,
        @SerializedName("sizes") val photoSizes: ArrayList<PhotoSize>
)