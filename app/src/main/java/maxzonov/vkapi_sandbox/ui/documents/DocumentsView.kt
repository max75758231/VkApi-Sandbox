package maxzonov.vkapi_sandbox.ui.documents

import maxzonov.vkapi_sandbox.data.docs.Doc

interface DocumentsView {
    fun showProgress()
    fun hideProgress()
    fun showDataFromServer(docs: ArrayList<Doc>)
    fun showDataError(errorStr: String)
}
