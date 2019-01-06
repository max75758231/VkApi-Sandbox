package maxzonov.vkapi_sandbox.data.wall

import com.google.gson.annotations.SerializedName
import maxzonov.vkapi_sandbox.data.photos.Comment
import maxzonov.vkapi_sandbox.data.photos.Like
import maxzonov.vkapi_sandbox.data.photos.Repost

data class WallPost(
    @SerializedName("from_id") val id: Long,
    @SerializedName("text") val text: String,
    @SerializedName("date") val date: Long,
    @SerializedName("attachments") val attachments: ArrayList<Attachment>,
    @SerializedName("can_delete") val canDelete: Short,
    @SerializedName("can_pin") val canPin: Short,
    @SerializedName("likes") val likes: Like,
    @SerializedName("comments") val comments: Comment,
    @SerializedName("reposts") val reposts: Repost
)