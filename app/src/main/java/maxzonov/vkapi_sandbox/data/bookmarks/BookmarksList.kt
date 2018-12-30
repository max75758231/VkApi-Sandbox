package maxzonov.vkapi_sandbox.data.bookmarks

import com.google.gson.annotations.SerializedName
import maxzonov.vkapi_sandbox.data.wall.WallProfile

data class BookmarksList(
    @SerializedName("count") val count: Int,
    @SerializedName("items") val bookmarks: ArrayList<Bookmark>,
    @SerializedName("profiles") val profiles: ArrayList<WallProfile>
)