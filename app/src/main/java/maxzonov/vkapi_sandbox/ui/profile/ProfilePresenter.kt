package maxzonov.vkapi_sandbox.ui.profile

import maxzonov.vkapi_sandbox.data.profile.Profile

class ProfilePresenter(private var view: ProfileActivity?, private val interactor: ProfileInteractor) : ProfileInteractor.OnLoadingResultListener {

    fun getProfileData() {
        view?.showProgress()
        interactor.getProfileDataFromNetwork(this)
    }

    fun onViewDestroyed() {
        view = null
    }

    override fun onResultSuccess(profile: Profile) {
        view?.showData(profile)
    }

    override fun onResultFail(errorStr: String) {
        view?.showDataError(errorStr)
        view?.hideProgress()
    }
}