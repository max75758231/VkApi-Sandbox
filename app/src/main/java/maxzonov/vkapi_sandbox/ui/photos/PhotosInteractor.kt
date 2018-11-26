package maxzonov.vkapi_sandbox.ui.photos

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import maxzonov.vkapi_sandbox.data.photos.Photo
import maxzonov.vkapi_sandbox.data.photos.Photos
import maxzonov.vkapi_sandbox.data.photos.ResponsePhotos
import maxzonov.vkapi_sandbox.retrofit.ApiServicePhoto
import maxzonov.vkapi_sandbox.retrofit.ApiServiceProfile
import maxzonov.vkapi_sandbox.retrofit.RetrofitClient
import maxzonov.vkapi_sandbox.utils.Constants
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class PhotosInteractor {

    interface OnLoadingResultListener {
        fun onResultSuccess(photos: ArrayList<Photo>)
        fun onResultFail(errorStr: String)
    }

    private lateinit var onLoadingResultListener: OnLoadingResultListener

    fun getPhotosDataFromNetwork(onLoadingResultListener: OnLoadingResultListener) {
        val userId = PrefsHelper.read(PrefsHelper.ID_USER, 0).toString()
        val token = PrefsHelper.read(PrefsHelper.TOKEN, "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiServicePhoto = RetrofitClient.getPhotoApiService()
        this.onLoadingResultListener = onLoadingResultListener

        compositeDisposable.add(
                apiService
                        .getInitialProfileResponse
                        (
                                Constants.VK_METHOD_PHOTOS,
                                userId,
                                token!!,
                                Constants.VK_API_VERSION
                        )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponseSuccess, this::handleResponseError)
        )
    }

    private fun handleResponseSuccess(response: ResponsePhotos) {
        val photos: ArrayList<Photo> = response.photos[0].photos
        onLoadingResultListener.onResultSuccess(photos)
    }

    private fun handleResponseError(error: Throwable) {
        Log.d("myLog", error.message)
        onLoadingResultListener.onResultFail(error.message.toString())
    }
}