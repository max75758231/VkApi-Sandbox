package maxzonov.vkapi_sandbox.data.docs

import com.google.gson.annotations.SerializedName

data class ResponseDocs(
    @SerializedName("response") val responseDocs: Docs
)