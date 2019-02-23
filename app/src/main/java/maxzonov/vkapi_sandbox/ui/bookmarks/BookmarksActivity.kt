package maxzonov.vkapi_sandbox.ui.bookmarks

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_bookmarks.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.post.ResponsePosts
import maxzonov.vkapi_sandbox.ui.base.BaseActivity
import maxzonov.vkapi_sandbox.ui.posts.PostsAdapter
import maxzonov.vkapi_sandbox.utils.ImageViewFormatter

class BookmarksActivity : BaseActivity(), BookmarksView {

    private lateinit var presenter: BookmarksPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarks)

        presenter = BookmarksPresenter(this, BookmarksInteractor())
        presenter.getBookmarksData()
    }

    override fun showDataFromServer(response: ResponsePosts) {
        val displayMetrics = resources.displayMetrics
        val deviceWidthInDp: Int = (displayMetrics.widthPixels / displayMetrics.density).toInt()
        val postsInfo = Triple(response.posts.responseItems, response.posts.profiles, response.posts.groups)
        val adapter = PostsAdapter(this, postsInfo, ImageViewFormatter(this, deviceWidthInDp))
        rv_bookmarks.layoutManager = LinearLayoutManager(this)
        rv_bookmarks.adapter = adapter
        hideProgress()
    }

    override fun showProgress() {
        pb_bookmarks.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_bookmarks.visibility = View.GONE
    }

    override fun showDataError(errorStr: String) {
        Toast.makeText(this, "Ошибка получения списка закладок", Toast.LENGTH_SHORT).show()
    }
}