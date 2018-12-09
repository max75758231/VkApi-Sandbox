package maxzonov.vkapi_sandbox.ui.documents

class DocumentsPresenter (private var view: DocumentsActivity?, private val interactor: DocumentsInteractor) :
        DocumentsInteractor.OnLoadingResultListener {

    fun getDocsData() {

        interactor.getGroupsDataFromNetwork(this)
    }

    fun onViewDestroyed() {
        view = null
    }

    override fun onResultSuccess() {

    }

    override fun onResultFail(errorStr: String) {

    }
}
