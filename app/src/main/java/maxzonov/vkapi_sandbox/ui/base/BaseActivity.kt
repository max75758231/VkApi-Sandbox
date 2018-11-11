package maxzonov.vkapi_sandbox.ui.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.ui.menu.MenuActivity
import maxzonov.vkapi_sandbox.ui.settings.SettingsActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_general, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.general_action_menu -> {
                startActivity(Intent(this, MenuActivity::class.java))
                overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
                true
            }
            R.id.general_action_settings ->  {
                startActivity(Intent(this, SettingsActivity::class.java))
                overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}