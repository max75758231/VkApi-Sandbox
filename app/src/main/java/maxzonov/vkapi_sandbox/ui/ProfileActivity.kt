package maxzonov.vkapi_sandbox.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
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
import maxzonov.vkapi_sandbox.utils.DateFormatter

class ProfileActivity : AppCompatActivity() {

    private var isBackButtonClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        layout_profile_progressbar.visibility = View.VISIBLE

        val prefs = this.getSharedPreferences("params", Context.MODE_PRIVATE)
        val userId: String = prefs.getLong("userId", 0).toString()
        val token: String = prefs.getString("token", "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiService = RetrofitClient.getApiService()

        compositeDisposable.add(
                apiService
                        .getInitialProfileResponse
                        (
                            Constants.VK_METHOD_USERS,
                            userId,
                            Constants.VK_FIELDS_PROFILE,
                            token,
                            Constants.VK_API_VERSION
                        )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponseSuccess, this::handleResponseError)
        )
    }

    override fun onBackPressed() {

        val backPressedDelay: Long = 2000

        if (isBackButtonClicked) {
            finish()
            return
        }

        isBackButtonClicked = true

        Toast.makeText(this, getString(R.string.main_exit_button), Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ isBackButtonClicked = false }, backPressedDelay)
    }

    private fun handleResponseSuccess(response: Response) {response.profiles[0].firstName
        val firstName = response.profiles[0].firstName
        val lastName = response.profiles[0].lastName
        tv_profile_name.text = "$firstName $lastName"
        tv_profile_bdate.text = response.profiles[0].birthDate
        tv_profile_hometown.text = response.profiles[0].homeTown
        tv_profile_current_school.text = response.profiles[0].currentSchool

        if (response.profiles[0].online == 1) {
            tv_profile_online.text = "Online"
        } else {
            val lastSeenDay = DateFormatter.convertDateToDayString(response.profiles[0].lastSeen.time)
            val lastSeenTime = DateFormatter.convertDateToTimeString(response.profiles[0].lastSeen.time)

            if (response.profiles[0].sex == 1)
                tv_profile_online.text = "Была $lastSeenDay в $lastSeenTime"
            else
                tv_profile_online.text = "Был $lastSeenDay в $lastSeenTime"
        }

        Glide.with(this).load(response.profiles[0].photoCropped).into(iv_profile_ava)

        layout_profile_progressbar.visibility = View.GONE
    }

    private fun handleResponseError(error: Throwable) {
        Toast.makeText(this, "Не удалось загрузить профиль. Проверьте интернет-соединение", Toast.LENGTH_LONG).show()
        Log.d("myLog", error.message)
    }
}
