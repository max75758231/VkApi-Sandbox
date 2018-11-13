package maxzonov.vkapi_sandbox

import android.app.Application
import maxzonov.vkapi_sandbox.utils.PrefsHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        PrefsHelper.init(applicationContext)
    }
}