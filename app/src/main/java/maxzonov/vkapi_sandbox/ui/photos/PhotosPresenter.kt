package maxzonov.vkapi_sandbox.ui.photos

import maxzonov.vkapi_sandbox.data.photos.Photo

class PhotosPresenter (private var view: PhotosActivity?, private val interactor: PhotosInteractor):
        PhotosInteractor.OnLoadingResultListener {

    fun getPhotosData() {
        view?.showProgress()
        interactor.getPhotosDataFromNetwork(this)
    }

    fun onViewDestroyed() {
        view = null
    }

    override fun onResultSuccess(photos: ArrayList<Photo>) {
        view?.hideProgress()
        view?.showDataWithRecyclerView(photos)
    }

    override fun onResultFail(errorStr: String) {
        view?.hideProgress()
        view?.showDataError(errorStr)
    }
}