package maxzonov.vkapi_sandbox.ui.documents

import android.util.Log

class DocumentsInteractor {

    interface OnLoadingResultListener {
        fun onResultSuccess()
        fun onResultFail(errorStr: String)
    }

    private lateinit var onLoadingResultListener: OnLoadingResultListener

    fun getGroupsDataFromNetwork(onLoadingResultListener: OnLoadingResultListener) {

    }

    private fun handleResponseSuccess() {

    }

    private fun handleResponseError(error: Throwable) {
        Log.e("myLog", error.message)
        onLoadingResultListener.onResultFail(error.message.toString())
    }
}
