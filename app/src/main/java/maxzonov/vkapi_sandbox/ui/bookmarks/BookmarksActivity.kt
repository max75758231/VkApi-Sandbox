package maxzonov.vkapi_sandbox.ui.bookmarks

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_bookmarks.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.bookmarks.ResponseBookmarks
import maxzonov.vkapi_sandbox.ui.base.BaseActivity

class BookmarksActivity : BaseActivity(), BookmarksView {

    private lateinit var presenter: BookmarksPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarks)

        presenter = BookmarksPresenter(this, BookmarksInteractor())
        presenter.getBookmarksData()
    }

    override fun showProgress() {
        pb_bookmarks.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_bookmarks.visibility = View.GONE
    }

    override fun showDataFromServer(response: ResponseBookmarks) {
        val adapter = BookmarksAdapter(this, response.response.bookmarks, response.response.profiles)
        rv_bookmarks.layoutManager = LinearLayoutManager(this)
        rv_bookmarks.adapter = adapter
        hideProgress()
    }

    override fun showDataError(errorStr: String) {
        Toast.makeText(this, "Ошибка получения списка закладок", Toast.LENGTH_SHORT).show()
    }
}