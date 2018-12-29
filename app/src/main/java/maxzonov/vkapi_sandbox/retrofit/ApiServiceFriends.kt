package maxzonov.vkapi_sandbox.retrofit

import io.reactivex.Observable
import maxzonov.vkapi_sandbox.data.friends.ResponseFriends
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceFriends {

    @GET("{method}?")
    fun getFriendsResponse(@Path("method") method: String,
                           @Query("user_id") userId: String,
                           @Query("order") sortBy: String,
                           @Query("fields") fields: String,
                           @Query("access_token") token: String,
                           @Query("v") version: String): Observable<ResponseFriends>
}