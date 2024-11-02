package com.seifmortada.applications.suprema.di

import android.annotation.SuppressLint
import android.content.Context
import com.seifmortada.applications.suprema.MyApplication
import com.seifmortada.applications.suprema.data.remote.apis.AuthService
import com.seifmortada.applications.suprema.data.remote.apis.DoorService
import com.seifmortada.applications.suprema.data.remote.apis.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val timeoutSeconds = 120L
    // Interceptor to add session ID (bs-session-id) from SharedPreferences
    val authInterceptor = Interceptor { chain ->
        val sharedPreferences =
            MyApplication.getContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val sessionId = sharedPreferences.getString("bs-session-id", null)
        val originalRequest: Request = chain.request()
        val newRequest = if (sessionId != null) {
            originalRequest.newBuilder()
                .header("bs-session-id", sessionId) // Add session ID to header
                .build()
        } else {
            originalRequest
        }
        chain.proceed(newRequest)
    }

    // Set up SSL context for trusting all certificates (unsafe)
    val trustAllCerts = arrayOf<TrustManager>(
        @SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(
                chain: Array<out java.security.cert.X509Certificate>,
                authType: String
            ) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(
                chain: Array<out java.security.cert.X509Certificate>,
                authType: String
            ) {
            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
        }
    )

    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(null, trustAllCerts, java.security.SecureRandom())
    return OkHttpClient.Builder()
        .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }
        .addInterceptor(interceptor)
        .addInterceptor(authInterceptor)
        .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
        .readTimeout(timeoutSeconds, TimeUnit.SECONDS)
        .writeTimeout(timeoutSeconds, TimeUnit.SECONDS)
        .build()
}

private val BASE_URL = "https://192.168.1.33:444/api/"

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideAuthService(): AuthService {
    return provideRetrofit().create(AuthService::class.java)
}

fun provideUserService(): UserService {
    return provideRetrofit().create(UserService::class.java)
}
    fun provideDoorService(): DoorService {
        return provideRetrofit().create(DoorService::class.java)

    }