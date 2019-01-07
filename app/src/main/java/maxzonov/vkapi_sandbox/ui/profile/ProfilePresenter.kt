package maxzonov.vkapi_sandbox.ui.profile

import maxzonov.vkapi_sandbox.data.groups.Group
import maxzonov.vkapi_sandbox.data.profile.Profile
import maxzonov.vkapi_sandbox.data.wall.WallPost
import maxzonov.vkapi_sandbox.data.wall.WallProfile

class ProfilePresenter(private var view: ProfileActivity?, private val interactor: ProfileInteractor) : ProfileInteractor.OnLoadingResultListener {

    fun getProfileData() {
        view?.showProgress()
        interactor.getProfileDataFromNetwork(this)
        interactor.getProfileWallData(this)
    }

    fun onViewDestroyed() {
        view = null
    }

    override fun onResultSuccess(profile: Profile) {
        view?.showProfileDataInfo(profile)
    }

    override fun onResultSuccess(wallPosts: ArrayList<WallPost>, profiles: ArrayList<WallProfile>, groups: ArrayList<Group>) {
        view?.showProfileWallPosts(wallPosts, profiles, groups)
    }

    override fun onResultFail(errorStr: String) {
        view?.showDataError(errorStr)
        view?.hideProgress()
    }
}