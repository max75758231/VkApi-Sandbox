package maxzonov.vkapi_sandbox.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.Response
import maxzonov.vkapi_sandbox.retrofit.ApiService
import maxzonov.vkapi_sandbox.retrofit.RetrofitClient
import maxzonov.vkapi_sandbox.utils.Constants

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = this.getSharedPreferences("params", Context.MODE_PRIVATE)
        val userId: String = prefs.getLong("userId", 0).toString()
        val token: String = prefs.getString("token", "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiService = RetrofitClient.getApiService()

        compositeDisposable.add(
                apiService.getInitialProfileResponse(Constants.METHOD_USERS, userId, "last_seen", token, Constants.API_VERSION)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponseSuccess, this::handleResponseError)
        )
    }

    private fun handleResponseSuccess(response: Response) {
        tv_profile_first_name.text = response.response[0].firstName
        tv_profile_last_name.text = response.response[0].lastName
        tv_profile_bdate.text = response.response[0].birthDate
        tv_profile_hometown.text = response.response[0].homeTown
        if (response.response[0].online == 1) {
            tv_profile_online.text = "Online"
        } else {
            tv_profile_online.text = "Offline"
        }

        Glide.with(this).load(response.response[0].photoCropped).into(iv_profile_ava)
    }

    private fun handleResponseError(error: Throwable) {
        Log.d("myLog", error.printStackTrace().toString())
    }
}
