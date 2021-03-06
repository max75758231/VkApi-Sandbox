package maxzonov.vkapi_sandbox.utils

class Constants {
    companion object {

        private const val VK_CLIENT_ID = "6707335"
        const val VK_REDIRECT_URI = "https://oauth.vk.com/blank.html"
        const val VK_AUTHORIZE_URI = "https://oauth.vk.com/authorize?"
        const val VK_API_VERSION = "5.92"
        const val VK_AUTH_URL = "https://oauth.vk.com/authorize?client_id=$VK_CLIENT_ID&display=page&" +
                "redirect_uri=$VK_REDIRECT_URI&scope=friends,docs&response_type=token&v=$VK_API_VERSION"
        const val VK_AUTH_URL_WITH_REGISTRATION = "https://oauth.vk.com/authorize?client_id=$VK_CLIENT_ID" +
                "&display=page&redirect_uri=$VK_REDIRECT_URI&scope=friends,docs&response_type=token&" +
                "v=$VK_API_VERSION&revoke=1"

        const val VK_METHOD_USERS = "users.get"
        const val VK_METHOD_PHOTOS = "photos.get"
        const val VK_METHOD_GROUPS = "groups.get"
        const val VK_METHOD_DOCS = "docs.get"
        const val VK_METHOD_WALL = "wall.get"
        const val VK_METHOD_FRIENDS = "friends.get"
        const val VK_METHOD_BOOKMARKS = "fave.getPosts"

        const val VK_FIELDS_PROFILE = "sex, online, home_town, bdate, photo_max, last_seen, education"

        const val VK_EXTENDED = "1"
        const val VK_NOT_EXTENDED = "0"

        const val VK_WALL_FILTER_ALL = "all"
        const val VK_WALL_FILTER_OWNER = "owner"
        const val VK_WALL_FILTER_OTHERS = "others"

        const val VK_FRIENDS_SORT_BY = "name"
        const val VK_FRIENDS_FIELDS = "domain,photo_100"
    }
}