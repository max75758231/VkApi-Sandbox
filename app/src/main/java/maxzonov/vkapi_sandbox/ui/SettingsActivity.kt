package maxzonov.vkapi_sandbox.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_settings.*
import maxzonov.vkapi_sandbox.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        layout_settings_exit.setOnClickListener {
            exitAccountAndClearSettings()
        }
    }

    private fun exitAccountAndClearSettings() {

        clearSetting()
        val intent = Intent(this, StartActivity::class.java)
        intent.putExtra("after_exit", true)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
        finish()
    }

    private fun clearSetting() {
        val prefs = this.getSharedPreferences("params", Context.MODE_PRIVATE)
        prefs.edit {
            putString("token", "")
            putLong("userId", 0)
        }
    }
}
