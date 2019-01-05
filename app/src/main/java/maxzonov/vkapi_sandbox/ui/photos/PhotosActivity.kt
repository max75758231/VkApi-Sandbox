package maxzonov.vkapi_sandbox.ui.photos

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_photos.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.photos.Photo
import maxzonov.vkapi_sandbox.ui.menu.MenuActivity
import maxzonov.vkapi_sandbox.ui.settings.SettingsActivity

class PhotosActivity : AppCompatActivity(), PhotosView {

    private lateinit var photosPresenter: PhotosPresenter
    private var photos: ArrayList<Photo> = ArrayList()
    private var isRecyclerViewShowed: Boolean = true
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        photosPresenter = PhotosPresenter(this, PhotosInteractor())
        photosPresenter.getPhotosData()
    }

    override fun onDestroy() {
        photosPresenter.onViewDestroyed()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_photos, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.photos_action_menu -> {
                startActivity(Intent(this, MenuActivity::class.java))
                overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
                true
            }
            R.id.photos_action_settings ->  {
                startActivity(Intent(this, SettingsActivity::class.java))
                overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
                true
            }
            R.id.photos_action_view ->  {
                if (isRecyclerViewShowed) {
                    isRecyclerViewShowed = false
                    menu!!.getItem(2).icon = ContextCompat.getDrawable(this, R.drawable.ic_photos_list)
                    showDataWithGridView()
                } else {
                    isRecyclerViewShowed = true
                    menu!!.getItem(2).icon = ContextCompat.getDrawable(this, R.drawable.ic_photos_grid)
                    showDataWithRecyclerView(photos)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showDataWithRecyclerView(photos: ArrayList<Photo>) {
        this.photos = photos
        val adapter = PhotosAdapter(this, photos)
        rv_photos.layoutManager = LinearLayoutManager(this)
        rv_photos.adapter = adapter

        hideGridView()
        showRecyclerView()
    }

    override fun showDataError(errorStr: String) {
        Toast.makeText(this, "Ошибка получения фото", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        pb_photos.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_photos.visibility = View.GONE
    }

    private fun showDataWithGridView() {
        val adapter = PhotosGridAdapter(this, photos)
        gridview_photos.adapter = adapter

        hideRecyclerView()
        showGridView()
    }

    private fun showRecyclerView() {
        rv_photos.visibility = View.VISIBLE
    }

    private fun hideRecyclerView() {
        rv_photos.visibility = View.GONE
    }

    private fun showGridView() {
        gridview_photos.visibility = View.VISIBLE
    }

    private fun hideGridView() {
        gridview_photos.visibility = View.GONE
    }
}