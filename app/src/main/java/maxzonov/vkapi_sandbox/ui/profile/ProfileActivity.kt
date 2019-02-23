package maxzonov.vkapi_sandbox.ui.profile

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_profile.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.groups.Group
import maxzonov.vkapi_sandbox.data.profile.Profile
import maxzonov.vkapi_sandbox.data.post.Post
import maxzonov.vkapi_sandbox.data.post.PostProfile
import maxzonov.vkapi_sandbox.ui.base.BaseActivity
import maxzonov.vkapi_sandbox.ui.posts.PostsAdapter
import maxzonov.vkapi_sandbox.utils.DateFormatter
import maxzonov.vkapi_sandbox.utils.ImageViewFormatter

class ProfileActivity : BaseActivity(), ProfileView {

    private var isBackButtonClicked = false
    private lateinit var profilePresenter: ProfilePresenter

    companion object {
        private const val DELAY_BETWEEN_BACK_PRESSED: Long = 2000

        private const val SEX_FEMALE = 1
        private const val ONLINE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profilePresenter = ProfilePresenter(this, ProfileInteractor())

        profilePresenter.getProfileData()
        hideWallData()
    }

    override fun onResume() {
        super.onResume()
        profilePresenter.getProfileData()
    }

    override fun onBackPressed() {
        if (isBackButtonClicked) {
            finish()
            return
        }

        requestPressBackButtonAgain()
    }

    override fun onDestroy() {
        profilePresenter.onViewDestroyed()
        super.onDestroy()
    }

    private fun requestPressBackButtonAgain() {
        isBackButtonClicked = true
        Toast.makeText(this, getString(R.string.general_back_button), Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            isBackButtonClicked = false
        }, DELAY_BETWEEN_BACK_PRESSED)
    }

    override fun showProfileDataInfo(profile: Profile) {
        showFullName(profile)
        tv_profile_bdate.text = profile.birthDate
        tv_profile_hometown.text = profile.homeTown
        tv_profile_current_school.text = profile.currentSchool

        showOnlineStatus(profile)

        Glide.with(this)
                .load(profile.photoCropped)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
                    ): Boolean {
                        hideProgress()
                        return false
                    }
                })
                .into(iv_profile_ava)
    }

    override fun showProfileWallPosts(wallPosts: ArrayList<Post>, profiles: ArrayList<PostProfile>, groups: ArrayList<Group>) {
        tv_profile_wall_count.text = getString(R.string.profile_wall_count, wallPosts.size.toString())

        val displayMetrics = resources.displayMetrics
        val deviceWidthInDp: Int = (displayMetrics.widthPixels / displayMetrics.density).toInt()
        val postsInfo = Triple(wallPosts, profiles, groups)
        val adapter = PostsAdapter(this, postsInfo, ImageViewFormatter(this, deviceWidthInDp))
        rv_profile_wall.layoutManager = LinearLayoutManager(this)
        rv_profile_wall.adapter = adapter
        pb_profile_wall.visibility = View.GONE
        showWallData()
    }

    override fun showDataError(errorStr: String) {
        Toast.makeText(this, errorStr, Toast.LENGTH_LONG).show()
    }

    private fun showFullName(profile: Profile) {
        val firstName = profile.firstName
        val lastName = profile.lastName
        tv_profile_name.text = "$firstName $lastName"
    }

    private fun showOnlineStatus(profile: Profile) {
        if (profile.online == ONLINE) {
            tv_profile_online.text = getString(R.string.profile_online)
        } else {
            showLastSeen(profile)
        }
    }

    private fun showLastSeen(profile: Profile) {
        val lastSeenDay = DateFormatter.convertDateToDayString(profile.lastSeen.time)
        val lastSeenTime = DateFormatter.convertDateToTimeString(profile.lastSeen.time)

        if (profile.sex == SEX_FEMALE)
            tv_profile_online.text = getString(R.string.profile_last_seen_woman, lastSeenDay, lastSeenTime)
        else
            tv_profile_online.text = getString(R.string.profile_last_seen_man, lastSeenDay, lastSeenTime)
    }

    override fun showProgress() {
        layout_profile_progressbar.visibility = View.VISIBLE
        pb_profile_wall.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        layout_profile_progressbar.visibility = View.GONE
    }

    private fun showWallData() {
        tv_profile_wall_count.visibility = View.VISIBLE
        rv_profile_wall.visibility = View.VISIBLE
    }

    private fun hideWallData() {
        tv_profile_wall_count.visibility = View.GONE
        rv_profile_wall.visibility = View.GONE
    }
}
