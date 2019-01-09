package maxzonov.vkapi_sandbox.ui.bookmarks

import maxzonov.vkapi_sandbox.data.post.ResponsePosts

interface BookmarksView {
    fun showProgress()
    fun hideProgress()
    fun showDataFromServer(response: ResponsePosts)
    fun showDataError(errorStr: String)
}