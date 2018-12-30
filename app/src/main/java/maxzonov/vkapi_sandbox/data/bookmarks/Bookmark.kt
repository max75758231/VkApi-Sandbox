package maxzonov.vkapi_sandbox.data.bookmarks

import com.google.gson.annotations.SerializedName
import maxzonov.vkapi_sandbox.data.photos.Comment
import maxzonov.vkapi_sandbox.data.photos.Like
import maxzonov.vkapi_sandbox.data.photos.Repost
import maxzonov.vkapi_sandbox.data.wall.WallProfile

data class Bookmark(
    @SerializedName("from_id") val fromId: Long,
    @SerializedName("date") val date: Long,
    @SerializedName("text") val text: String,
    @SerializedName("likes") val likes: Like,
    @SerializedName("comments") val comments: Comment,
    @SerializedName("reposts") val reposts: Repost,
    @SerializedName("profiles") val profiles: ArrayList<WallProfile>
)