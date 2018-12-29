package maxzonov.vkapi_sandbox.ui.friends

import maxzonov.vkapi_sandbox.data.friends.FriendsList
import maxzonov.vkapi_sandbox.data.friends.ResponseFriends

interface FriendsView {
    fun showProgress()
    fun hideProgress()
    fun showDataFromServer(friendsResponse: ResponseFriends)
    fun showDataError(errorStr: String)
}