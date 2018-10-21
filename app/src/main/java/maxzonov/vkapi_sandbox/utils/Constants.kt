package maxzonov.vkapi_sandbox.utils

class Constants {
    companion object {

        const val VK_CLIENT_ID = "6707335"
        const val VK_REDIRECT_URI = "https://oauth.vk.com/blank.html"
        const val VK_API_VERSION = "5.85"
        const val VK_AUTH_URL = "https://oauth.vk.com/authorize?client_id=$VK_CLIENT_ID&display=page&" +
                "redirect_uri=$VK_REDIRECT_URI&scope=friends&response_type=token&v=$VK_API_VERSION"

        const val VK_METHOD_USERS = "users.get"
        const val VK_FIELDS_PROFILE = "sex, online, home_town, bdate, photo_max, last_seen, education"
    }
}