package com.toreva.mobile.di

import android.content.Context
import com.toreva.mobile.data.api.TorevaApi
import com.toreva.mobile.data.repository.AuthRepository
import com.toreva.mobile.data.repository.IntentRepository
import com.toreva.mobile.data.repository.ReceiptRepository
import com.toreva.mobile.data.repository.VaultRepository
import com.toreva.mobile.data.session.SessionStore
import com.toreva.mobile.data.wallet.MwaClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMwaClient(@ApplicationContext context: Context): MwaClient = MwaClient(context)

    @Provides
    fun provideAuthRepository(api: TorevaApi, mwaClient: MwaClient, sessionStore: SessionStore) =
        AuthRepository(api, mwaClient, sessionStore)

    @Provides
    fun provideVaultRepository(api: TorevaApi, sessionStore: SessionStore, mwaClient: MwaClient) =
        VaultRepository(api, sessionStore, mwaClient)

    @Provides
    fun provideIntentRepository(api: TorevaApi) = IntentRepository(api)

    @Provides
    fun provideReceiptRepository(api: TorevaApi) = ReceiptRepository(api)
}
