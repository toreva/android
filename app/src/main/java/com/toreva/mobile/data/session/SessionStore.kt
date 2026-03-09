package com.toreva.mobile.data.session

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

class SessionStore(context: Context) {
    private val dataStore = PreferenceDataStoreFactory.create(
        produceFile = { context.preferencesDataStoreFile("toreva_session.preferences_pb") },
    )

    private object Keys {
        val token = stringPreferencesKey("jwt_token")
        val wallet = stringPreferencesKey("wallet_address")
        val accountId = stringPreferencesKey("account_id")
        val vaultId = stringPreferencesKey("vault_id")
        val vaultStatus = stringPreferencesKey("vault_status")
        val mwaToken = stringPreferencesKey("mwa_auth_token")
    }

    private val prefs: Flow<Preferences> = dataStore.data.catch {
        if (it is IOException) emit(emptyPreferences()) else throw it
    }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Volatile
    private var cachedToken: String? = null

    init {
        scope.launch {
            prefs.map { it[Keys.token] }.collect { token ->
                cachedToken = token
            }
        }
    }

    fun getCachedToken(): String? = cachedToken

    suspend fun saveSession(token: String, walletAddress: String, accountId: String, vaultId: String?, vaultStatus: String, mwaToken: String) {
        cachedToken = token
        dataStore.edit {
            it[Keys.token] = token
            it[Keys.wallet] = walletAddress
            it[Keys.accountId] = accountId
            it[Keys.vaultStatus] = vaultStatus
            it[Keys.mwaToken] = mwaToken
            if (vaultId != null) it[Keys.vaultId] = vaultId
        }
    }

    suspend fun getToken(): String? = prefs.map { it[Keys.token] }.first()
    suspend fun getMwaToken(): String? = prefs.map { it[Keys.mwaToken] }.first()
    suspend fun getWalletAddress(): String? = prefs.map { it[Keys.wallet] }.first()
    suspend fun getVaultId(): String? = prefs.map { it[Keys.vaultId] }.first()

    suspend fun clear() {
        cachedToken = null
        dataStore.edit { it.clear() }
    }
}
