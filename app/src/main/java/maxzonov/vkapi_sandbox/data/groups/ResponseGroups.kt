package maxzonov.vkapi_sandbox.data.groups

import com.google.gson.annotations.SerializedName

data class ResponseGroups(
        @SerializedName("response") val photos: Groups
)