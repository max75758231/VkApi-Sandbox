package maxzonov.vkapi_sandbox.data.friends

import com.google.gson.annotations.SerializedName

data class ResponseFriends(
    @SerializedName("response") val response: FriendsList
)