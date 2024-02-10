package bes.max.mobinttest.companies.data.database

import androidx.room.Entity
import bes.max.mobinttest.companies.domain.models.Company

@Entity("companies_table")
data class CompanyEntity(
    val companyId: String,
    val companyName: String,
    val loyaltyLevelNumber: Int,
    val loyaltyLevelName: String,
    val loyaltyLevelRequiredSum: Int,
    val loyaltyLevelMarkToCash: Int,
    val loyaltyLevelCashToMark: Int,
    val loyaltyLevelMark: Int,
    val logo: String,
    val backgroundColor: String,
    val mainColor: String,
    val cardBackgroundColor: String,
    val textColor: String,
    val highlightTextColor: String,
    val accentColor: String
)

fun CompanyEntity.map(): Company = Company(
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
