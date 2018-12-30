package maxzonov.vkapi_sandbox.data.bookmarks

import com.google.gson.annotations.SerializedName

data class BookmarksList(
    @SerializedName("count") val count: Int,
    @SerializedName("items") val bookmarks: ArrayList<Bookmark>
)