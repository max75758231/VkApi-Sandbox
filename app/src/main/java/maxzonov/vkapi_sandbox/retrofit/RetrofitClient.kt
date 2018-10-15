package maxzonov.vkapi_sandbox.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private const val BASE_URL = "https://api.vk.com/method/"
        private val httpClient: OkHttpClient = OkHttpClient()
        private val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create()

        private val retrofitInstance: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(gsonConverterFactory)
                .build()

        fun getApiService(): ApiService {
            return retrofitInstance.create(ApiService::class.java)
        }
    }

}