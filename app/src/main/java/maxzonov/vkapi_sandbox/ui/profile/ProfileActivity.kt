package maxzonov.vkapi_sandbox.ui.profile

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile.*
import maxzonov.vkapi_sandbox.R
import android.widget.Toast
import maxzonov.vkapi_sandbox.ui.base.BaseActivity
import maxzonov.vkapi_sandbox.data.profile.Profile
import maxzonov.vkapi_sandbox.utils.DateFormatter

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

    override fun showData(profile: Profile) {
        showFullName(profile)
        tv_profile_bdate.text = profile.birthDate
        tv_profile_hometown.text = profile.homeTown
        tv_profile_current_school.text = profile.currentSchool

        showOnlineStatus(profile)

        Glide.with(this)
                .load(profile.photoCropped)
                .into(iv_profile_ava)

        hideProgress()
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
    }

    override fun hideProgress() {
        layout_profile_progressbar.visibility = View.GONE
    }
}
