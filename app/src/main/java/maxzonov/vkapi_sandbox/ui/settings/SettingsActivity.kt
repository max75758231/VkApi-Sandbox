package maxzonov.vkapi_sandbox.ui.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.ui.menu.MenuActivity
import maxzonov.vkapi_sandbox.ui.start.StartActivity
import maxzonov.vkapi_sandbox.utils.PrefsHelper

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
        exitToStartActivity()
    }

    private fun clearSetting() {
        with(PrefsHelper) {
            write(PrefsHelper.TOKEN, "")
            write(PrefsHelper.ID_USER, 0)
        }
    }

    private fun exitToStartActivity() {
        val intent = Intent(this, StartActivity::class.java)
        intent.putExtra("after_exit", true)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.general_action_menu -> {
                startActivity(Intent(this, MenuActivity::class.java))
                overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
