package maxzonov.vkapi_sandbox.ui.profile

import maxzonov.vkapi_sandbox.data.groups.Group
import maxzonov.vkapi_sandbox.data.profile.Profile
import maxzonov.vkapi_sandbox.data.post.Post
import maxzonov.vkapi_sandbox.data.post.PostProfile

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

    override fun onResultSuccess(postsInfo: Triple<ArrayList<Post>, ArrayList<PostProfile>, ArrayList<Group>>) {
        view?.showProfileWallPosts(postsInfo)
    }

    override fun onResultFail(errorStr: String) {
        view?.showDataError(errorStr)
        view?.hideProgress()
    }
}