package maxzonov.vkapi_sandbox.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_profile.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.Response
import maxzonov.vkapi_sandbox.retrofit.ApiService
import maxzonov.vkapi_sandbox.retrofit.RetrofitClient
import maxzonov.vkapi_sandbox.utils.Constants
import android.widget.Toast
import maxzonov.vkapi_sandbox.BaseActivity
import maxzonov.vkapi_sandbox.data.Profile
import maxzonov.vkapi_sandbox.utils.DateFormatter
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class ProfileActivity : BaseActivity() {

    private var isBackButtonClicked = false

    companion object {
        private const val DELAY_BETWEEN_BACK_PRESSED: Long = 2000

        private const val SEX_FEMALE = 1
        private const val ONLINE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        layout_profile_progressbar.visibility = View.VISIBLE

        val userId = PrefsHelper.read(PrefsHelper.ID_USER, 0).toString()
        val token = PrefsHelper.read(PrefsHelper.TOKEN, "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiService = RetrofitClient.getApiService()

        compositeDisposable.add(
                apiService
                        .getInitialProfileResponse
                        (
                            Constants.VK_METHOD_USERS,
                            userId,
                            Constants.VK_FIELDS_PROFILE,
                            token!!,
                            Constants.VK_API_VERSION
                        )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponseSuccess, this::handleResponseError)
        )

        btn_profile_expand_info.setOnClickListener {
            Toast.makeText(this, "Скоро здесь будет информация", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (isBackButtonClicked) {
            finish()
            return
        }

        requestPressBackButtonAgain()
    }

    private fun requestPressBackButtonAgain() {
        isBackButtonClicked = true
        Toast.makeText(this, getString(R.string.general_back_button), Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            isBackButtonClicked = false
        }, DELAY_BETWEEN_BACK_PRESSED)
    }

    private fun handleResponseSuccess(response: Response) {
        val profile: Profile = response.profiles[0]

        showProfileInfo(profile)
    }

    private fun showProfileInfo(profile: Profile) {
        showFullName(profile)
        tv_profile_bdate.text = profile.birthDate
        tv_profile_hometown.text = profile.homeTown
        tv_profile_current_school.text = profile.currentSchool

        showOnlineStatus(profile)

        Glide.with(this)
                .load(profile.photoCropped)
                .into(iv_profile_ava)

        layout_profile_progressbar.visibility = View.GONE
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

    private fun handleResponseError(error: Throwable) {
        Toast.makeText(this, getString(R.string.profile_error), Toast.LENGTH_LONG).show()
        Log.d("myLog", error.message)
    }
}
