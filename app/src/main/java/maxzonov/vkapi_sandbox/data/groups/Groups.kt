package maxzonov.vkapi_sandbox.data.groups

import com.google.gson.annotations.SerializedName

data class Groups(
        @SerializedName("items") val groups: ArrayList<Group>
)