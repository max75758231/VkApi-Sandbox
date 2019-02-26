package maxzonov.vkapi_sandbox.ui.profile

import maxzonov.vkapi_sandbox.data.groups.Group
import maxzonov.vkapi_sandbox.data.profile.Profile
import maxzonov.vkapi_sandbox.data.post.Post
import maxzonov.vkapi_sandbox.data.post.PostProfile

interface ProfileView {

    fun showProgress()
    fun hideProgress()
    fun showProfileDataInfo(profile: Profile)
    fun showProfileWallPosts(postsInfo: Triple<ArrayList<Post>, ArrayList<PostProfile>, ArrayList<Group>>)
    fun showDataError(errorStr: String)
}