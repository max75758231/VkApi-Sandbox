package maxzonov.vkapi_sandbox.data.groups

import com.google.gson.annotations.SerializedName

data class Group(
        @SerializedName("id") val groudId: Long,
        @SerializedName("name") val groupName: String,
        @SerializedName("type") val typeName: String,
        @SerializedName("photo_100") val groupPhotoUrl: String
)