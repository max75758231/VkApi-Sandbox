package maxzonov.vkapi_sandbox.ui.start

import android.net.Uri
import maxzonov.vkapi_sandbox.utils.Constants
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class StartInteractor {

    interface OnEnterAccountResult {
        fun onResultSuccess()
        fun onResultAuthorize()
        fun onResultFail()
    }

    private lateinit var onEnterAccountResult: OnEnterAccountResult

    fun getParamsFromVkRedirectUrl(onEnterAccountResult: OnEnterAccountResult, url: String) {
        this.onEnterAccountResult = onEnterAccountResult
        if (url.startsWith(Constants.VK_REDIRECT_URI) && !url.contains("access_denied")) {
            val urlModified = url.replace('#', '?')

            val accessToken: String? = Uri.parse(urlModified).getQueryParameter("access_token")
            val userId: Long? = Uri.parse(urlModified).getQueryParameter("user_id").toLong()

            if (accessToken != null && userId != null) {
                writeParamsToPrefs(accessToken, userId)
                onEnterAccountResult.onResultSuccess()
            }
        } else if (url.startsWith(Constants.VK_AUTHORIZE_URI)) {
            onEnterAccountResult.onResultAuthorize()
        } else {
            onEnterAccountResult.onResultFail()
            return
        }
    }

    private fun writeParamsToPrefs(token: String, userId: Long) {
        with(PrefsHelper) {
            write(TOKEN, token)
            write(ID_USER, userId)
        }
    }
}