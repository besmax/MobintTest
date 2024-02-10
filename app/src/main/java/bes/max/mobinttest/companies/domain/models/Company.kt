package bes.max.mobinttest.companies.domain.models

import bes.max.mobinttest.companies.data.database.CompanyEntity

data class Company(
    val companyId: String,
    val companyName: String,
    val loyaltyLevelNumber: String,
    val loyaltyLevelName: String,
    val loyaltyLevelRequiredSum: String,
    val loyaltyLevelMarkToCash: String,
    val loyaltyLevelCashToMark: String,
    val loyaltyLevelMark: Int,
    val logo: String,
    val backgroundColor: String,
    val mainColor: String,
    val cardBackgroundColor: String,
    val textColor: String,
    val highlightTextColor: String,
    val accentColor: String
)

fun Company.map(): CompanyEntity = CompanyEntity(
    companyId = companyId,
    companyName = companyName,
    loyaltyLevelNumber = loyaltyLevelNumber,
    loyaltyLevelName = loyaltyLevelName,
    loyaltyLevelRequiredSum = loyaltyLevelRequiredSum,
    loyaltyLevelMarkToCash = loyaltyLevelMarkToCash,
    loyaltyLevelCashToMark = loyaltyLevelCashToMark,
    loyaltyLevelMark = loyaltyLevelMark,
    logo = logo,
    backgroundColor = backgroundColor,
    mainColor = mainColor,
    cardBackgroundColor = cardBackgroundColor,
    textColor = textColor,
    highlightTextColor = highlightTextColor,
    accentColor = accentColor
)