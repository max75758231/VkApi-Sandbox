package maxzonov.vkapi_sandbox.retrofit

import io.reactivex.Observable
import maxzonov.vkapi_sandbox.data.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{method}?")
    fun getInitialProfileResponse(@Path("method") method: String,
                                  @Query("user_ids") userId: String,
                                  @Query("fields") fields: String,
                                  @Query("access_token") token: String,
                                  @Query("v") version: String) : Observable<Response>
}