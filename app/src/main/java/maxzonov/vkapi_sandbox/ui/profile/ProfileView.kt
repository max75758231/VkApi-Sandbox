package maxzonov.vkapi_sandbox.ui.profile

import maxzonov.vkapi_sandbox.data.Profile

interface ProfileView {

    fun showProgress()
    fun hideProgress()
    fun showData(profile: Profile)
    fun showDataError(errorStr: String)
}