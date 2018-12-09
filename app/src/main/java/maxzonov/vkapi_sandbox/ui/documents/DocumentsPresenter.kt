package maxzonov.vkapi_sandbox.ui.documents

import maxzonov.vkapi_sandbox.data.docs.Doc

class DocumentsPresenter (private var view: DocumentsActivity?, private val interactor: DocumentsInteractor) :
        DocumentsInteractor.OnLoadingResultListener {

    fun getDocsData() {
        view?.showProgress()
        interactor.getGroupsDataFromNetwork(this)
    }

    fun onViewDestroyed() {
        view = null
    }

    override fun onResultSuccess(docs: ArrayList<Doc>) {
        view?.hideProgress()
        view?.showData(docs)
    }

    override fun onResultFail(errorStr: String) {
        view?.hideProgress()
        view?.showDataError(errorStr)
    }
}
