package maxzonov.vkapi_sandbox.data.photos

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("date") val photoDate: String,
        @SerializedName("sizes") val photoSizes: ArrayList<PhotoSize>,
        @SerializedName("likes") val photoLikes: Like,
        @SerializedName("reposts") val photoReposts: Repost,
        @SerializedName("comments") val photoComments: Comment
)