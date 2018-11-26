package maxzonov.vkapi_sandbox.data.photos

import com.google.gson.annotations.SerializedName

data class ResponsePhotos(
        @SerializedName("response") val photos: ArrayList<Photos>
)