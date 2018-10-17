package maxzonov.vkapi_sandbox.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start.*
import maxzonov.vkapi_sandbox.R
import android.webkit.WebResourceRequest
import android.os.Build
import android.annotation.TargetApi



class StartActivity : AppCompatActivity() {

    companion object {
        private const val CLIENT_ID = "6707335"
        private const val REDIRECT_URI = "https://oauth.vk.com/blank.html"
        private const val API_VERSION = "5.85"
        private const val AUTH_URL = "https://oauth.vk.com/authorize?client_id=$CLIENT_ID&display=page&" +
                "redirect_uri=$REDIRECT_URI&scope=friends&response_type=token&v=$API_VERSION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btn_sign_in.setOnClickListener {
            btn_sign_in.visibility = View.GONE

            webview_login.settings.javaScriptEnabled = true
            webview_login.isVerticalScrollBarEnabled = false
            webview_login.isHorizontalScrollBarEnabled = false
            webview_login.clearCache(true)

            webview_login.visibility = View.VISIBLE
            webview_login.webViewClient = VkWebViewClient()
            webview_login.loadUrl(AUTH_URL)
        }
    }

    inner class VkWebViewClient : WebViewClient() {

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            parseUrl(url)
        }
    }

    private fun parseUrl(url: String?) {

        if (url == null) {
            Toast.makeText(this, "Вернулся неверный url", Toast.LENGTH_LONG).show()
            return
        }

        var newUrl = url.replace('#', '?')

        val accessToken: String? = Uri.parse(newUrl).getQueryParameter("access_token")
        val userId: Long? = Uri.parse(newUrl).getQueryParameter("user_id").toLong()

//        Log.d("myLog", "token: $accessToken userId: $userId")

        if (accessToken != null && userId != null) {
            writeParamsToPrefs(accessToken, userId)
            webview_login.visibility = View.GONE
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun writeParamsToPrefs(token: String, userId: Long) {
        val prefs = this.getSharedPreferences("params", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("token", token)
        editor.putLong("userId", userId)
        editor.apply()
    }
}
