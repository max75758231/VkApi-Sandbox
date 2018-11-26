package maxzonov.vkapi_sandbox.ui.photos

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_photos.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.photos.Photo
import maxzonov.vkapi_sandbox.ui.base.BaseActivity

class PhotosActivity : BaseActivity(), PhotosView {

    private lateinit var photosPresenter: PhotosPresenter

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

    override fun showData(photos: ArrayList<Photo>) {
        val adapter = PhotosAdapter(this, photos)
        rv_photos.layoutManager = LinearLayoutManager(this)
        rv_photos.adapter = adapter
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
}
