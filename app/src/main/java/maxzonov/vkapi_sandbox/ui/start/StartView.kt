package maxzonov.vkapi_sandbox.ui.start

interface StartView {

    fun showVkButton()
    fun hideVkButton()

    fun showProgress()
    fun hideProgress()

    fun showWebview()
    fun hideWebview()

    fun startAppActivity()
}