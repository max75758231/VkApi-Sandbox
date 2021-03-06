package maxzonov.vkapi_sandbox.ui.bookmarks

import maxzonov.vkapi_sandbox.data.post.ResponsePosts

class BookmarksPresenter(private var view: BookmarksActivity?, private val interactor: BookmarksInteractor) :
    BookmarksInteractor.OnLoadingResultListener {

    fun getBookmarksData() {
        view?.showProgress()
        interactor.getBookmarksDataFromNetwork(this)
    }

    fun onViewDestroyed() {
        view = null
    }

    override fun onResultSuccess(response: ResponsePosts) {
        view?.showDataFromServer(response)
    }

    override fun onResultFail(errorStr: String) {
        view?.showDataError(errorStr)
        view?.hideProgress()
    }
}