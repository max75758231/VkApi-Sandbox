package maxzonov.vkapi_sandbox.ui.bookmarks

import maxzonov.vkapi_sandbox.data.bookmarks.ResponseBookmarks

interface BookmarksView {
    fun showProgress()
    fun hideProgress()
    fun showDataFromServer(response: ResponseBookmarks)
    fun showDataError(errorStr: String)
}