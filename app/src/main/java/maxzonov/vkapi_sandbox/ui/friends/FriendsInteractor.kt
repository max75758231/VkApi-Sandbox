package maxzonov.vkapi_sandbox.ui.friends

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import maxzonov.vkapi_sandbox.data.friends.ResponseFriends
import maxzonov.vkapi_sandbox.retrofit.ApiServiceFriends
import maxzonov.vkapi_sandbox.retrofit.RetrofitClient
import maxzonov.vkapi_sandbox.utils.Constants
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class FriendsInteractor {

    interface OnLoadingResultListener {
        fun onResultSuccess(friendsResponse: ResponseFriends)
        fun onResultFail(errorStr: String)
    }

    private lateinit var onLoadingResultListener: OnLoadingResultListener

    fun getFriendsDataFromNetwork(onLoadingResultListener: OnLoadingResultListener) {
        val userId = PrefsHelper.read(PrefsHelper.ID_USER, 0).toString()
        val token = PrefsHelper.read(PrefsHelper.TOKEN, "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiServiceFriends = RetrofitClient.getFriendsService()
        this.onLoadingResultListener = onLoadingResultListener

        compositeDisposable.add(
            apiService
                .getFriendsResponse(
                    Constants.VK_METHOD_DOCS,
                    userId,
                    Constants.VK_FRIENDS_SORT_BY,
                    Constants.VK_FRIENDS_FIELDS,
                    token!!,
                    Constants.VK_API_VERSION
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseSuccess, this::handleResponseError)
        )
    }

    private fun handleResponseSuccess(response: ResponseFriends) {
        onLoadingResultListener.onResultSuccess(response)
    }

    private fun handleResponseError(error: Throwable) {
        Log.e("myLog", error.message)
        onLoadingResultListener.onResultFail(error.message.toString())
    }
}