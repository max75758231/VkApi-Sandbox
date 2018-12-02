package maxzonov.vkapi_sandbox.ui.groups

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import maxzonov.vkapi_sandbox.data.groups.Group
import maxzonov.vkapi_sandbox.data.groups.ResponseGroups
import maxzonov.vkapi_sandbox.retrofit.ApiServiceGroups
import maxzonov.vkapi_sandbox.retrofit.RetrofitClient
import maxzonov.vkapi_sandbox.utils.Constants
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class GroupsInteractor {

    interface OnLoadingResultListener {
        fun onResultSuccess(photos: ArrayList<Group>)
        fun onResultFail(errorStr: String)
    }

    private lateinit var onLoadingResultListener: OnLoadingResultListener

    fun getGroupsDataFromNetwork(onLoadingResultListener: OnLoadingResultListener) {
        val userId = PrefsHelper.read(PrefsHelper.ID_USER, 0).toString()
        val token = PrefsHelper.read(PrefsHelper.TOKEN, "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiServiceGroups = RetrofitClient.getGroupsApiService()

        this.onLoadingResultListener = onLoadingResultListener

        compositeDisposable.add(
                apiService.getGroupsResponse (
                        Constants.VK_METHOD_GROUPS,
                        userId,
                        token!!,
                        "1",
                        Constants.VK_API_VERSION
                )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponseSuccess, this::handleResponseError)
        )
    }

    private fun handleResponseSuccess(response: ResponseGroups) {
        val photos: ArrayList<Group> = response.groups.groups
        onLoadingResultListener.onResultSuccess(photos)
    }

    private fun handleResponseError(error: Throwable) {
        Log.e("myLog", error.message)
        onLoadingResultListener.onResultFail(error.message.toString())
    }
}