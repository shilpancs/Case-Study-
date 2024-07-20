package com.target.casestudy.target.di

import com.target.casestudy.target.CaseStudyApplication
import com.target.casestudy.target.network.DealsService
import com.target.casestudy.target.network.HttpInterceptor
import com.target.casestudy.target.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun dealsAPI(retrofit: Retrofit): DealsService {
        return retrofit.create(DealsService::class.java)
    }

    private fun getClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .cache(
                Cache(
                    File(CaseStudyApplication.instance.applicationContext.cacheDir, "http-cache"),
                    10L * 1024L * 1024L
                )
            )
            .addNetworkInterceptor(HttpInterceptor())
            .build()

        httpClient.addInterceptor(logging)
        return httpClient.build()
    }
}