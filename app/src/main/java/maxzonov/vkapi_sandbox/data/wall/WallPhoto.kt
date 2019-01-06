package maxzonov.vkapi_sandbox.data.wall

import com.google.gson.annotations.SerializedName
import maxzonov.vkapi_sandbox.data.photos.PhotoSize

data class WallPhoto(
    @SerializedName("sizes") val photoSizes: ArrayList<PhotoSize>
)