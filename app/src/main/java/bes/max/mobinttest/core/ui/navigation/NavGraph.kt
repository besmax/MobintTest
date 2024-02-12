package bes.max.mobinttest.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import bes.max.mobinttest.companies.ui.CompanyCardsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.CompanyCardsScreen.route) {

        composable(route = Screen.CompanyCardsScreen.route) {
            CompanyCardsScreen()
        }
    }

}