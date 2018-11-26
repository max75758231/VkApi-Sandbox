package maxzonov.vkapi_sandbox.data.photos

import com.google.gson.annotations.SerializedName

data class Photos(
        @SerializedName("items") val photos: ArrayList<Photo>
)