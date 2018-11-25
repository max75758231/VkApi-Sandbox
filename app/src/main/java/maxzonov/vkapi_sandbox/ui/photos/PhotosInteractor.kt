package maxzonov.vkapi_sandbox.ui.photos

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import maxzonov.vkapi_sandbox.retrofit.ApiService
import maxzonov.vkapi_sandbox.retrofit.RetrofitClient
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class PhotosInteractor {

    interface OnLoadingResultListener {
        fun onResultSuccess(photos: ArrayList<String>)
        fun onResultFail(errorStr: String)
    }

    private lateinit var onLoadingResultListener: OnLoadingResultListener

    fun getPhotosDataFromNetwork(onLoadingResultListener: OnLoadingResultListener) {
        val userId = PrefsHelper.read(PrefsHelper.ID_USER, 0).toString()
        val token = PrefsHelper.read(PrefsHelper.TOKEN, "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiService = RetrofitClient.getApiService()
        this.onLoadingResultListener = onLoadingResultListener


    }

    private fun handleResponseSuccess() {

    }

    private fun handleResponseError(error: Throwable) {
        Log.d("myLog", error.message)
        onLoadingResultListener.onResultFail(error.message.toString())
    }
}