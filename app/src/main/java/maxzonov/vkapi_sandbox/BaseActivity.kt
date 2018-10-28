package maxzonov.vkapi_sandbox

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import maxzonov.vkapi_sandbox.ui.MenuActivity

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
                Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}