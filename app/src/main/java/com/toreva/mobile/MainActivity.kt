package com.toreva.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.toreva.mobile.navigation.NavGraph
import com.toreva.mobile.data.wallet.MwaClient
import com.toreva.mobile.ui.theme.TorevaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorevaTheme {
                NavGraph()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        MwaClient.setCurrentActivity(this)
    }

    override fun onPause() {
        MwaClient.setCurrentActivity(null)
        super.onPause()
    }
}
