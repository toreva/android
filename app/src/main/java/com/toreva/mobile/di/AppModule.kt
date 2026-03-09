package com.toreva.mobile.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.toreva.mobile.BuildConfig
import com.toreva.mobile.data.api.AuthInterceptor
import com.toreva.mobile.data.api.TorevaApi
import com.toreva.mobile.data.session.SessionStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSessionStore(@ApplicationContext context: Context): SessionStore = SessionStore(context)

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideOkHttp(authInterceptor: AuthInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(authInterceptor).build()

    @Provides
    @Singleton
    fun provideAuthInterceptor(sessionStore: SessionStore): AuthInterceptor = AuthInterceptor(sessionStore)

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient, moshi: Moshi): TorevaApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TOREVA_API_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TorevaApi::class.java)
    }
}
