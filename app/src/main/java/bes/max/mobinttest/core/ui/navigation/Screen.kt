package bes.max.mobinttest.core.ui.navigation

sealed class Screen(
    val route: String
) {
    object SplashScreen : Screen("splashScreen")
    object CompanyCardsScreen : Screen("companyCardsScreen")
}