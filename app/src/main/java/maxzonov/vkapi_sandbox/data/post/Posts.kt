package maxzonov.vkapi_sandbox.data.post

import com.google.gson.annotations.SerializedName
import maxzonov.vkapi_sandbox.data.groups.Group

data class Posts(
    @SerializedName("count") val count: Int,
    @SerializedName("items") val responseItems: ArrayList<Post>,
    @SerializedName("profiles") val profiles: ArrayList<PostProfile>,
    @SerializedName("groups") val groups: ArrayList<Group>
)