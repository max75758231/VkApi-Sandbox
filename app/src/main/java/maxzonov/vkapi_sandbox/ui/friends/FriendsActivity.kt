package maxzonov.vkapi_sandbox.ui.friends

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_friends.*
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
        presenter.onViewDestroyed()
        super.onDestroy()
    }

    override fun showProgress() {
        pb_friends.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_friends.visibility = View.GONE
    }

    override fun showDataFromServer(friendsResponse: ResponseFriends) {
        tv_friends_count.text = getString(R.string.friends_count, friendsResponse.response.count.toString())

        val adapter = FriendsAdapter(this, friendsResponse.response.items)
        rv_friends.layoutManager = LinearLayoutManager(this)
        rv_friends.adapter = adapter
    }

    override fun showDataError(errorStr: String) {
        Toast.makeText(this, "Ошибка получения списка друзей", Toast.LENGTH_SHORT).show()
    }
}