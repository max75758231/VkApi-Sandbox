package maxzonov.vkapi_sandbox.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_menu.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.ui.bookmarks.BookmarksActivity
import maxzonov.vkapi_sandbox.ui.documents.DocumentsActivity
import maxzonov.vkapi_sandbox.ui.friends.FriendsActivity
import maxzonov.vkapi_sandbox.ui.groups.GroupsActivity
import maxzonov.vkapi_sandbox.ui.photos.PhotosActivity
import maxzonov.vkapi_sandbox.ui.settings.SettingsActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        layout_menu_friends.setOnClickListener {
            startActivity(Intent(this, FriendsActivity::class.java))
            overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
        }

        layout_menu_groups.setOnClickListener {
            startActivity(Intent(this, GroupsActivity::class.java))
            overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
        }

        layout_menu_photos.setOnClickListener {
            startActivity(Intent(this, PhotosActivity::class.java))
            overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
        }

        layout_menu_bookmarks.setOnClickListener {
            startActivity(Intent(this, BookmarksActivity::class.java))
            overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
        }

        layout_menu_documents.setOnClickListener {
            startActivity(Intent(this, DocumentsActivity::class.java))
            overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.general_action_settings ->  {
                startActivity(Intent(this, SettingsActivity::class.java))
                overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
