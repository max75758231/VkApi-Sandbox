package maxzonov.vkapi_sandbox.data.friends

import com.google.gson.annotations.SerializedName

data class FriendsList(
    @SerializedName("count") val count: Int,
    @SerializedName("items") val items: ArrayList<Friend>
)