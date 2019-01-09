package maxzonov.vkapi_sandbox.data.post

import com.google.gson.annotations.SerializedName
import maxzonov.vkapi_sandbox.data.photos.PhotoSize

data class PostPhoto(
    @SerializedName("sizes") val photoSizes: ArrayList<PhotoSize>
)