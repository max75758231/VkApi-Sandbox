package maxzonov.vkapi_sandbox.data.docs

import com.google.gson.annotations.SerializedName

data class Doc(
    @SerializedName("title") val title: String,
    @SerializedName("size") val size: Long,
    @SerializedName("url") val url: String,
    @SerializedName("date") val date: Long,
    @SerializedName("type") val type: Short
)