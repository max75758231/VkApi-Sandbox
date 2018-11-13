package maxzonov.vkapi_sandbox.ui.start

class StartPresenter(private var view: StartActivity?, private val interactor: StartInteractor): StartInteractor.OnEnterAccountResult {

    fun loginToVk(url: String) {
        view?.showProgress()
        interactor.getParamsFromVkRedirectUrl(this, url)
    }

    override fun onResultSuccess() {
        view?.hideWebview()
        view?.startAppActivity()
    }

    override fun onResultAuthorize() {
        view?.hideProgress()
    }

    override fun onResultFail() {
        view?.showVkButton()
        view?.hideProgress()
    }

    fun onViewDestroyed() {
        view = null
    }
}