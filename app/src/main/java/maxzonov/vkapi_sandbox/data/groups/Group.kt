package maxzonov.vkapi_sandbox.data.groups

import com.google.gson.annotations.SerializedName

data class Group(
        @SerializedName("name") val groupName: String,
        @SerializedName("type") val typeName: String,
        @SerializedName("photo_200") val groupSize: String
)