package maxzonov.vkapi_sandbox.retrofit

import io.reactivex.Observable
import maxzonov.vkapi_sandbox.data.photos.ResponsePhotos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServicePhoto {

    @GET("{method}?")
    fun getPhotoResponse(@Path("method") method: String,
                         @Query("owner_id") userId: String,
                         @Query("access_token") token: String,
                         @Query("album_id") albumId: String,
                         @Query("v") version: String): Observable<ResponsePhotos>
}