package maxzonov.vkapi_sandbox.data.bookmarks

import com.google.gson.annotations.SerializedName

data class ResponseBookmarks(
    @SerializedName("response") val response: BookmarksList
)