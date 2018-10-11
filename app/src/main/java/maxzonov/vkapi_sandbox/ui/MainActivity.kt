package maxzonov.vkapi_sandbox.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import maxzonov.vkapi_sandbox.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = this.getSharedPreferences("params", Context.MODE_PRIVATE)
        textView.text = prefs.getLong("userId", 0).toString()
    }
}
