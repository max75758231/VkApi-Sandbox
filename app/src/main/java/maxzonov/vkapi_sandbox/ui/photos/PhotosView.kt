package maxzonov.vkapi_sandbox.ui.photos

import maxzonov.vkapi_sandbox.data.photos.Photo

interface PhotosView {

    fun showProgress()
    fun hideProgress()
    fun showDataWithRecyclerView(photos: ArrayList<Photo>)
    fun showDataError(errorStr: String)
}