package maxzonov.vkapi_sandbox.retrofit

import io.reactivex.Observable
import maxzonov.vkapi_sandbox.data.docs.ResponseDocs
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceDocs {
    @GET("{method}?")
    fun getDocsResponse(@Path("method") method: String,
                        @Query("owner_id") userId: String,
                        @Query("access_token") token: String,
                        @Query("v") version: String): Observable<ResponseDocs>
}