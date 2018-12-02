package maxzonov.vkapi_sandbox.retrofit

import io.reactivex.Observable
import maxzonov.vkapi_sandbox.data.groups.ResponseGroups
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceGroups {
    @GET("{method}?")
    fun getGroupsResponse(@Path("method") method: String,
                         @Query("user_id") userId: String,
                         @Query("access_token") token: String,
                         @Query("extended") albumId: String,
                         @Query("v") version: String): Observable<ResponseGroups>
}