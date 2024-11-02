package com.seifmortada.applications.suprema.di

import com.seifmortada.applications.suprema.data.remote.apis.AuthService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    //=============Retrofit===============//
    single { provideOkHttpClient() }
    single { provideRetrofit() }
    //===============Apis===============//
    single { provideAuthService() }
    single { provideUserService() }
    single { provideDoorService() }
}
