package maxzonov.vkapi_sandbox.ui.profile

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import maxzonov.vkapi_sandbox.data.groups.Group
import maxzonov.vkapi_sandbox.data.profile.Profile
import maxzonov.vkapi_sandbox.data.profile.ResponseProfile
import maxzonov.vkapi_sandbox.data.post.ResponsePosts
import maxzonov.vkapi_sandbox.data.post.Post
import maxzonov.vkapi_sandbox.data.post.PostProfile
import maxzonov.vkapi_sandbox.retrofit.ApiServiceProfile
import maxzonov.vkapi_sandbox.retrofit.ApiServiceWall
import maxzonov.vkapi_sandbox.retrofit.RetrofitClient
import maxzonov.vkapi_sandbox.utils.Constants
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class ProfileInteractor {

    interface OnLoadingResultListener {
        fun onResultSuccess(profile: Profile)
        fun onResultSuccess(postsInfo: Triple<ArrayList<Post>, ArrayList<PostProfile>, ArrayList<Group>>)
        fun onResultFail(errorStr: String)
    }

    private lateinit var onLoadingResultListener: OnLoadingResultListener

    fun getProfileDataFromNetwork(onLoadingResultListener: OnLoadingResultListener) {
        val userId = PrefsHelper.read(PrefsHelper.ID_USER, 0).toString()
        val token = PrefsHelper.read(PrefsHelper.TOKEN, "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiServiceProfile = RetrofitClient.getProfileApiService()
        this.onLoadingResultListener = onLoadingResultListener

        compositeDisposable.add(
                apiService
                        .getInitialProfileResponse(
                            Constants.VK_METHOD_USERS,
                            userId,
                            Constants.VK_FIELDS_PROFILE,
                            token!!,
                            Constants.VK_API_VERSION
                        )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponseSuccess, this::handleResponseError)
        )
    }

    private fun handleResponseSuccess(response: ResponseProfile) {
        val profile: Profile = response.profiles[0]
        onLoadingResultListener.onResultSuccess(profile)
    }

    private fun handleResponseError(error: Throwable) {
        Log.e("myLog", error.message)
        onLoadingResultListener.onResultFail(error.message.toString())
    }

    fun getProfileWallData(onLoadingResultListener: OnLoadingResultListener) {
        val userId = PrefsHelper.read(PrefsHelper.ID_USER, 0).toString()
        val token = PrefsHelper.read(PrefsHelper.TOKEN, "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiServiceWall = RetrofitClient.getWallApiService()
        this.onLoadingResultListener = onLoadingResultListener

        compositeDisposable.add(
            apiService
                .getInitialWallResponse
                    (
                    Constants.VK_METHOD_WALL,
                    userId,
                    Constants.VK_EXTENDED,
                    Constants.VK_WALL_FILTER_ALL,
                    token!!,
                    Constants.VK_API_VERSION
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWallResponseSuccess, this::handleWallResponseError)
        )
    }

    private fun handleWallResponseSuccess(response: ResponsePosts) {
        val wallPosts: ArrayList<Post> = response.posts.responseItems
        val profiles: ArrayList<PostProfile> = response.posts.profiles
        val groups: ArrayList<Group> = response.posts.groups
        val postsInfo: Triple<ArrayList<Post>, ArrayList<PostProfile>, ArrayList<Group>> = Triple(wallPosts, profiles, groups)
        onLoadingResultListener.onResultSuccess(postsInfo)
    }

    private fun handleWallResponseError(error: Throwable) {
        Log.e("myLog", error.message)
        onLoadingResultListener.onResultFail(error.message.toString())
    }
}