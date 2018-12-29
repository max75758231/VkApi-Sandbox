package maxzonov.vkapi_sandbox.ui.friends

import android.os.Bundle
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.friends.ResponseFriends
import maxzonov.vkapi_sandbox.ui.base.BaseActivity

class FriendsActivity : BaseActivity(), FriendsView {

    private lateinit var presenter: FriendsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        presenter = FriendsPresenter(this, FriendsInteractor())
        presenter.getFriendsData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showDataFromServer(friendsResponse: ResponseFriends) {

    }

    override fun showDataError(errorStr: String) {

    }
}
