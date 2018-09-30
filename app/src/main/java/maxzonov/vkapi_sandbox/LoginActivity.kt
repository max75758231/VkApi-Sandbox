package maxzonov.vkapi_sandbox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val CLIENT_ID = "6707335"
    val REDIRECT_URI = "https://oauth.vk.com/blank.html"
    val AUTH_URL = "https://oauth.vk.com/authorize?client_id=$CLIENT_ID&display=page&" +
            "redirect_uri=$REDIRECT_URI&scope=friends&response_type=token&v=5.52"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        webview_login.settings.javaScriptEnabled = true
        webview_login.isVerticalScrollBarEnabled = false
        webview_login.isHorizontalScrollBarEnabled = false
        webview_login.clearCache(true)

        webview_login.loadUrl(AUTH_URL)

    }
}
