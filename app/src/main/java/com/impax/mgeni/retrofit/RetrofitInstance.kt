package com.impax.mgeni.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance
{
    companion object {
       // private const val BASE_URL: String = "http://10.0.0.122:80/api/"
      // private const val BASE_URL: String = "http://192.168.137.1:80/api/"
      //  private const val BASE_URL: String  = "http://20.86.117.62:8101/api/";  //dev envt
        private const val BASE_URL: String = "http://192.168.8.112:80/api/"  //local envt
        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}