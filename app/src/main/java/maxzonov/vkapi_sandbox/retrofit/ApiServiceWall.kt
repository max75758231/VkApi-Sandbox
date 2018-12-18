package maxzonov.vkapi_sandbox.retrofit

import io.reactivex.Observable
import maxzonov.vkapi_sandbox.data.wall.ResponseWall
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceWall {

    @GET("{method}?")
    fun getInitialWallResponse(@Path("method") method: String,
                                  @Query("owner_id") userId: String,
                                  @Query("extended") extended: String,
                                  @Query("filter") filter: String,
                                  @Query("access_token") token: String,
                                  @Query("v") version: String) : Observable<ResponseWall>
}