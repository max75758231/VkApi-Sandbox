package maxzonov.vkapi_sandbox.ui.profile

import maxzonov.vkapi_sandbox.data.profile.Profile
import maxzonov.vkapi_sandbox.data.wall.WallPost
import maxzonov.vkapi_sandbox.data.wall.WallProfile

interface ProfileView {

    fun showProgress()
    fun hideProgress()
    fun showProfileDataInfo(profile: Profile)
    fun showProfileWallPosts(wallPosts: ArrayList<WallPost>, profiles: ArrayList<WallProfile>)
    fun showDataError(errorStr: String)
}