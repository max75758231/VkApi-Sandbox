package maxzonov.vkapi_sandbox.ui.documents

interface DocumentsView {
    fun showProgress()
    fun hideProgress()
    fun showData()
    fun showDataError(errorStr: String)
}
