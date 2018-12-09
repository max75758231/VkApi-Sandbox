package maxzonov.vkapi_sandbox.data.docs

import com.google.gson.annotations.SerializedName

data class Docs(
    @SerializedName("items") val docs: ArrayList<Doc>
)