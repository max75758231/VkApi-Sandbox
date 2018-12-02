package maxzonov.vkapi_sandbox.ui.groups

import maxzonov.vkapi_sandbox.data.groups.Group

interface GroupsView {

    fun showProgress()
    fun hideProgress()
    fun showData(groups: ArrayList<Group>)
    fun showDataError(errorStr: String)
}
