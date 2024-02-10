package bes.max.mobinttest.companies.data.dto

import bes.max.mobinttest.companies.data.database.CompanyEntity

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

fun CompanyDto.map(): CompanyEntity = CompanyEntity(
    companyId = company.companyId,
    companyName = mobileAppDashboard.companyName,
    loyaltyLevelNumber = customerMarkParameters.loyaltyLevel.number,
    loyaltyLevelName = customerMarkParameters.loyaltyLevel.name,
    loyaltyLevelRequiredSum = customerMarkParameters.loyaltyLevel.requiredSum,
    loyaltyLevelMarkToCash = customerMarkParameters.loyaltyLevel.markToCash,
    loyaltyLevelCashToMark = customerMarkParameters.loyaltyLevel.cashToMark,
    loyaltyLevelMark = customerMarkParameters.mark,
    logo = mobileAppDashboard.logo,
    backgroundColor = mobileAppDashboard.backgroundColor,
    mainColor = mobileAppDashboard.mainColor,
    cardBackgroundColor = mobileAppDashboard.cardBackgroundColor,
    textColor = mobileAppDashboard.textColor,
    highlightTextColor = mobileAppDashboard.highlightTextColor,
    accentColor = mobileAppDashboard.accentColor
)