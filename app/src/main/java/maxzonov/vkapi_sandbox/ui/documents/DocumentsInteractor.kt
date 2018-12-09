package maxzonov.vkapi_sandbox.ui.documents

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import maxzonov.vkapi_sandbox.data.docs.Doc
import maxzonov.vkapi_sandbox.data.docs.ResponseDocs
import maxzonov.vkapi_sandbox.retrofit.ApiServiceDocs
import maxzonov.vkapi_sandbox.retrofit.RetrofitClient
import maxzonov.vkapi_sandbox.utils.Constants
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class DocumentsInteractor {

    interface OnLoadingResultListener {
        fun onResultSuccess(docs: ArrayList<Doc>)
        fun onResultFail(errorStr: String)
    }

    private lateinit var onLoadingResultListener: OnLoadingResultListener

    fun getGroupsDataFromNetwork(onLoadingResultListener: OnLoadingResultListener) {
        val userId = PrefsHelper.read(PrefsHelper.ID_USER, 0).toString()
        val token = PrefsHelper.read(PrefsHelper.TOKEN, "")

        val compositeDisposable = CompositeDisposable()
        val apiService: ApiServiceDocs = RetrofitClient.getDocsApiService()
        this.onLoadingResultListener = onLoadingResultListener

        compositeDisposable.add(
            apiService
                .getDocsResponse(
                    Constants.VK_METHOD_DOCS,
                    userId,
                    token!!,
                    Constants.VK_API_VERSION
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponseSuccess, this::handleResponseError)
        )
    }

    private fun handleResponseSuccess(response: ResponseDocs) {
        val docs: ArrayList<Doc> = response.responseDocs.docs
        onLoadingResultListener.onResultSuccess(docs)
    }

    private fun handleResponseError(error: Throwable) {
        Log.e("myLog", error.message)
        onLoadingResultListener.onResultFail(error.message.toString())
    }
}
