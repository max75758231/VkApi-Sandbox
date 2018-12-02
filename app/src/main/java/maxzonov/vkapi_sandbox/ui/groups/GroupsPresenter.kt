package maxzonov.vkapi_sandbox.ui.groups

import maxzonov.vkapi_sandbox.data.groups.Group

class GroupsPresenter (private var view: GroupsActivity?, private val interactor: GroupsInteractor):
        GroupsInteractor.OnLoadingResultListener {

    fun getPhotosData() {
        view?.showProgress()
        interactor.getGroupsDataFromNetwork(this)
    }

    fun onViewDestroyed() {
        view = null
    }

    override fun onResultSuccess(photos: ArrayList<Group>) {
        view?.hideProgress()
        view?.showData(photos)
    }

    override fun onResultFail(errorStr: String) {
        view?.hideProgress()
        view?.showDataError(errorStr)
    }
}