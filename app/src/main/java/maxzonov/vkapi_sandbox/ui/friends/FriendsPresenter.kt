package maxzonov.vkapi_sandbox.ui.friends

import maxzonov.vkapi_sandbox.data.friends.ResponseFriends

class FriendsPresenter(private var view: FriendsActivity?, private val interactor: FriendsInteractor) :
    FriendsInteractor.OnLoadingResultListener {

    fun getFriendsData() {
        view?.showProgress()
        interactor.getFriendsDataFromNetwork(this)
    }

    fun onViewDestroyed() {
        view = null
    }

    override fun onResultSuccess(friendsResponse: ResponseFriends) {
        view?.hideProgress()
        view?.showDataFromServer(friendsResponse)
    }

    override fun onResultFail(errorStr: String) {
        view?.hideProgress()
        view?.showDataError(errorStr)
    }
}