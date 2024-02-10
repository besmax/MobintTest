package bes.max.mobinttest.companies.data.dto

data class CompanyDto(
    val company: CompanyId,
    val customerMarkParameters: CustomerMarkParametersDto,
    val mobileAppDashboard: MobileAppDashboardDto,
)

data class CompanyId(
    val companyId: String
)

data class CustomerMarkParametersDto(
    val loyaltyLevel: LoyaltyLevelDto,
    val mark: Int
)

data class LoyaltyLevelDto(
    val number: Int,
    val name: String,
    val requiredSum: Int,
    val markToCash: Int,
    val cashToMark: Int
)

data class MobileAppDashboardDto(
    val companyName: String,
    val logo: String,
    val backgroundColor: String,
    val mainColor: String,
    val cardBackgroundColor: String,
    val textColor: String,
    val highlightTextColor: String,
    val accentColor: String
)