package maxzonov.vkapi_sandbox.ui.start

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start.*
import maxzonov.vkapi_sandbox.R
import android.graphics.Bitmap
import android.util.Log
import maxzonov.vkapi_sandbox.ui.profile.ProfileActivity
import maxzonov.vkapi_sandbox.utils.Constants
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        PrefsHelper.init(applicationContext)

        btn_sign_in.setOnClickListener {
            btn_sign_in.visibility = View.GONE
            pb_start.visibility = View.VISIBLE

            if (intent != null && intent.getBooleanExtra("after_exit", false)) {
                setupWebview(Constants.VK_AUTH_URL_WITH_REGISTRATION, VkWebViewClient())
            } else {
                setupWebview(Constants.VK_AUTH_URL, VkWebViewClient())
            }
        }
    }

    private fun setupWebview(authUrl: String, wvClient: WebViewClient) {
        with(webview_login) {
            settings.javaScriptEnabled = true
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
            clearCache(true)

            visibility = View.VISIBLE
            webViewClient = wvClient
            loadUrl(authUrl)
        }
    }

    inner class VkWebViewClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            if (url != null && url.startsWith(Constants.VK_REDIRECT_URI))
                webview_login.visibility = View.GONE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            url?.let { getParamsFromVkRedirectUrl(it) }
        }
    }

    private fun getParamsFromVkRedirectUrl(url: String) {

        pb_start.visibility = View.VISIBLE

        Log.d("myLog", "url: $url")

        if (url.startsWith(Constants.VK_REDIRECT_URI) && !url.contains("access_denied")) {
            val urlModified = url.replace('#', '?')

            val accessToken: String? = Uri.parse(urlModified).getQueryParameter("access_token")
            val userId: Long? = Uri.parse(urlModified).getQueryParameter("user_id").toLong()

            if (accessToken != null && userId != null) {
                writeParamsAndStartActivity(accessToken, userId)
            }
        } else if (url.startsWith(Constants.VK_AUTHORIZE_URI)) {
            pb_start.visibility = View.GONE
        } else {
            btn_sign_in.visibility = View.VISIBLE
            pb_start.visibility = View.GONE
            Toast.makeText(this, "Вернулся неверный url", Toast.LENGTH_LONG).show()
            return
        }
    }

    private fun writeParamsAndStartActivity(accessToken: String, userId: Long) {
        writeParamsToPrefs(accessToken, userId)

        webview_login.visibility = View.GONE

        val intent = Intent(this, ProfileActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
        finish()
    }

    private fun writeParamsToPrefs(token: String, userId: Long) {
        with(PrefsHelper) {
            write(PrefsHelper.TOKEN, token)
            write(PrefsHelper.ID_USER, userId)
        }
    }
}
