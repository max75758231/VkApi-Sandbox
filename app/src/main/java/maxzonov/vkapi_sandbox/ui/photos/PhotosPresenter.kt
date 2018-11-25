package maxzonov.vkapi_sandbox.ui.photos

class PhotosPresenter (private var view: PhotosActivity?, private val interactor: PhotosInteractor):
        PhotosInteractor.OnLoadingResultListener {

    fun getPhotosData() {
        view?.showProgress()
    }

    fun onViewDestroyed() {
        view = null
    }

    override fun onResultSuccess(photos: ArrayList<String>) {
        view?.hideProgress()
        view?.showData(photos)
    }

    override fun onResultFail(errorStr: String) {
        view?.hideProgress()
        view?.showDataError(errorStr)
    }
}