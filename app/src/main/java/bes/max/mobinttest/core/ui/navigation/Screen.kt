package bes.max.mobinttest.core.ui.navigation

sealed class Screen(
    val route:String
) {
    object CompanyCardsScreen : Screen("companyCardsScreen")
}