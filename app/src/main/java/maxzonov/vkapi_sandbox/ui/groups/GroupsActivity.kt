package maxzonov.vkapi_sandbox.ui.groups

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_groups.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.groups.Group
import maxzonov.vkapi_sandbox.ui.base.BaseActivity

class GroupsActivity : BaseActivity(), GroupsView {

    private lateinit var groupsPresenter: GroupsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)

        groupsPresenter = GroupsPresenter(this, GroupsInteractor())
        groupsPresenter.getPhotosData()
    }

    override fun onDestroy() {
        groupsPresenter.onViewDestroyed()
        super.onDestroy()
    }

    override fun showData(groups: ArrayList<Group>) {
        val groupsAdapter = GroupsAdapter(this, groups)
        rv_groups.layoutManager = LinearLayoutManager(this)
        rv_groups.adapter = groupsAdapter
    }

    override fun showDataError(errorStr: String) {
        Toast.makeText(this, "Ошибка получения списка групп", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        pb_groups.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_groups.visibility = View.GONE
    }
}