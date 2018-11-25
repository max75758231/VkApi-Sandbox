package maxzonov.vkapi_sandbox.ui.photos

interface PhotosView {

    fun showProgress()
    fun hideProgress()
    fun showData(photos: ArrayList<String>)
    fun showDataError(errorStr: String)
}