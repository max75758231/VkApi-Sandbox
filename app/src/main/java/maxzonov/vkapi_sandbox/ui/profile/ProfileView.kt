package maxzonov.vkapi_sandbox.ui.profile

import maxzonov.vkapi_sandbox.data.profile.Profile
import maxzonov.vkapi_sandbox.data.wall.WallPost

interface ProfileView {

    fun showProgress()
    fun hideProgress()
    fun showProfileDataInfo(profile: Profile)
    fun showProfileWallPosts(wallPosts: ArrayList<WallPost>)
    fun showDataError(errorStr: String)
}