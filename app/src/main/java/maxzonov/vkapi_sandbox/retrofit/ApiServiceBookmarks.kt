package maxzonov.vkapi_sandbox.retrofit

import io.reactivex.Observable
import maxzonov.vkapi_sandbox.data.bookmarks.ResponseBookmarks
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceBookmarks {
    @GET("{method}?")
    fun getBookmarksResponse(@Path("method") method: String,
                             @Query("extended") isExtended: String,
                             @Query("access_token") token: String,
                             @Query("v") version: String): Observable<ResponseBookmarks>
}