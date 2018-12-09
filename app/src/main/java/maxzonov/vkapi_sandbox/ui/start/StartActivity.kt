package maxzonov.vkapi_sandbox.ui.start

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.ui.profile.ProfileActivity
import maxzonov.vkapi_sandbox.utils.Constants

class StartActivity : AppCompatActivity(), StartView {

    private lateinit var startPresenter: StartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        startPresenter = StartPresenter(this, StartInteractor())

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

    override fun onDestroy() {
        startPresenter.onViewDestroyed()
        super.onDestroy()
    }

    private fun setupWebview(authUrl: String, wvClient: WebViewClient) {
        showWebview()
        with(webview_login) {
            settings.javaScriptEnabled = true
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
            clearCache(true)

//            visibility = View.VISIBLE
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

            url?.let {
                startPresenter.loginToVk(it)
            }
        }
    }

    override fun showVkButton() {
        btn_sign_in.visibility = View.VISIBLE
    }

    override fun hideVkButton() {
        btn_sign_in.visibility = View.GONE
    }

    override fun showProgress() {
        pb_start.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_start.visibility = View.GONE
    }

    override fun showWebview() {
        webview_login.visibility = View.VISIBLE
    }

    override fun hideWebview() {
        webview_login.visibility = View.GONE
    }

    override fun startAppActivity() {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
        finish()
    }
}