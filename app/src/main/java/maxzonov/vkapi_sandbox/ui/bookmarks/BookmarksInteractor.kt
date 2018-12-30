package maxzonov.vkapi_sandbox.ui.bookmarks

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import maxzonov.vkapi_sandbox.data.bookmarks.ResponseBookmarks
import maxzonov.vkapi_sandbox.retrofit.ApiServiceBookmarks
import maxzonov.vkapi_sandbox.retrofit.RetrofitClient
import maxzonov.vkapi_sandbox.utils.Constants
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class BookmarksInteractor {

    interface OnLoadingResultListener {
        fun onResultSuccess(response: ResponseBookmarks)
        fun onResultFail(errorStr: String)
    }

    private lateinit var onLoadingResultListener: OnLoadingResultListener

    fun getBookmarksDataFromNetwork(onLoadingResultListener: OnLoadingResultListener) {
        val userId = PrefsHelper.read(PrefsHelper.ID_USER, 0).toString()
        val token = PrefsHelper.read(PrefsHelper.TOKEN, "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiServiceBookmarks = RetrofitClient.getBookmarksService()
        this.onLoadingResultListener = onLoadingResultListener

        compositeDisposable.add(
            apiService
                .getBookmarksResponse(
                    Constants.VK_METHOD_BOOKMARKS,
                    "1",
                    token!!,
                    Constants.VK_API_VERSION
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseSuccess, this::handleResponseError)
        )
    }

    private fun handleResponseSuccess(response: ResponseBookmarks) {
        onLoadingResultListener.onResultSuccess(response)
    }

    private fun handleResponseError(error: Throwable) {
        Log.e("myLog", error.message)
        onLoadingResultListener.onResultFail(error.message.toString())
    }
}