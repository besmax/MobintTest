package bes.max.mobinttest.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import bes.max.mobinttest.core.ui.navigation.NavigationGraph
import bes.max.mobinttest.core.ui.theme.MobintTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobintTestTheme {
                val navController = rememberNavController()

                Scaffold { paddingValues ->
                    Box(
                        modifier = Modifier.padding(paddingValues)
                    ) {

                        NavigationGraph(navController)
                    }
                }
            }
        }
    }
}
