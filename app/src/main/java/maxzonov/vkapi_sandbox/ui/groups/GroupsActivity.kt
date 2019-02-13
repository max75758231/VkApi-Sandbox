package maxzonov.vkapi_sandbox.ui.groups

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_groups.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.groups.Group
import maxzonov.vkapi_sandbox.ui.menu.MenuActivity
import maxzonov.vkapi_sandbox.ui.settings.SettingsActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import maxzonov.vkapi_sandbox.ui.base.BaseActivity
import java.util.concurrent.TimeUnit

class GroupsActivity : BaseActivity(), GroupsView {

    private lateinit var groupsPresenter: GroupsPresenter

    private lateinit var groupsAdapter: GroupsAdapter
    private lateinit var groups: ArrayList<Group>

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_groups, menu)

        val menuSearchItem = menu!!.findItem(R.id.general_action_search)
        val searchView = menuSearchItem.actionView as SearchView
        searchView.maxWidth = 480
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@GroupsActivity, query, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Observable.create(ObservableOnSubscribe<String> { subscriber ->
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextChange(newText: String?): Boolean {
                            subscriber.onNext(newText!!)
                            return false
                        }

                        override fun onQueryTextSubmit(query: String?): Boolean {
                            subscriber.onNext(query!!)
                            return false
                        }
                    })
                })
                    .map { text -> text.toLowerCase().trim() }
                    .debounce(250, TimeUnit.MILLISECONDS)
                    .distinct()
                    .subscribe { text ->
//                        Log.d("myLog", "subscriber: $text")
                        updateRecyclerView(text)
                    }
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.general_action_search -> {
                true
            }
            R.id.general_action_menu -> {
                startActivity(Intent(this, MenuActivity::class.java))
                overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
                true
            }
            R.id.general_action_settings ->  {
                startActivity(Intent(this, SettingsActivity::class.java))
                overridePendingTransition(R.anim.move_right_activity_out, R.anim.move_left_activity_in)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun updateRecyclerView(filter: String) {
        var newGroups: ArrayList<Group> = ArrayList()
        for (group in groups) {
            if (group.groupName.toLowerCase().contains(filter)) {
                newGroups.add(group)
            }
        }
        runOnUiThread {
            groupsAdapter.updateGroups(newGroups)
        }

    }

    override fun showData(groups: ArrayList<Group>) {
        groupsAdapter = GroupsAdapter(this, groups)
        this.groups = groups
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