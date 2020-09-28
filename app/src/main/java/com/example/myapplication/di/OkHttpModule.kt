package com.example.myapplication.di

import com.example.myapplication.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class OkHttpModule(
    private val connectTimeout: Long = DEFAULT_CONNECT_TIMEOUT_MILLIS,
    private val readTimeout: Long = DEFAULT_READ_TIMEOUT_MILLIS,
    private val writeTimeout: Long = DEFAULT_WRITE_TIMEOUT_MILLIS
) {

    @Provides
    @Singleton
    @Named("httpClient")
    fun provideOkHttp(
        logInterceptor: HttpLoggingInterceptor?
    ): OkHttpClient {
       return OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
            .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
            .addInterceptor(logInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttps(@Named("httpClient") httpClient: OkHttpClient): OkHttpClient {
        return httpClient.newBuilder()
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor? {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            null
        }
    }

    companion object {
        const val DEFAULT_CONNECT_TIMEOUT_MILLIS: Long = 15 * 1000 // 15s
        const val DEFAULT_READ_TIMEOUT_MILLIS: Long = 20 * 1000 // 20s
        const val DEFAULT_WRITE_TIMEOUT_MILLIS: Long = 20 * 1000 // 20s
    }
}