package maxzonov.vkapi_sandbox.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*
import maxzonov.vkapi_sandbox.R

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
}
