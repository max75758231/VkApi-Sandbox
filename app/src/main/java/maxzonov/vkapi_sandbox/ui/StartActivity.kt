package maxzonov.vkapi_sandbox.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start.*
import maxzonov.vkapi_sandbox.R
import android.graphics.Bitmap
import android.util.Log
import maxzonov.vkapi_sandbox.utils.Constants

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

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

    private fun setupWebview(authUrl: String, webViewClient: WebViewClient) {
        webview_login.settings.javaScriptEnabled = true
        webview_login.isVerticalScrollBarEnabled = false
        webview_login.isHorizontalScrollBarEnabled = false
        webview_login.clearCache(true)

        webview_login.visibility = View.VISIBLE
        webview_login.webViewClient = webViewClient
        webview_login.loadUrl(authUrl)
    }

    inner class VkWebViewClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            if (url != null && url.startsWith(Constants.VK_REDIRECT_URI))
                webview_login.visibility = View.GONE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            getParamsFromVkRedirectUrl(url)
        }
    }

    private fun getParamsFromVkRedirectUrl(url: String?) {

        pb_start.visibility = View.VISIBLE

        Log.d("myLog", "url: $url")

        if (url != null && url.startsWith(Constants.VK_REDIRECT_URI) && !url.contains("access_denied")) {
            val urlModified = url.replace('#', '?')

            val accessToken: String? = Uri.parse(urlModified).getQueryParameter("access_token")
            val userId: Long? = Uri.parse(urlModified).getQueryParameter("user_id").toLong()

            if (accessToken != null && userId != null) {
                writeParamsAndStartActivity(accessToken, userId)
            }
        } else if (url != null && url.startsWith(Constants.VK_AUTHORIZE_URI)) {
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
        val prefs = this.getSharedPreferences("params", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("token", token)
        editor.putLong("userId", userId)
        editor.apply()
    }
}
