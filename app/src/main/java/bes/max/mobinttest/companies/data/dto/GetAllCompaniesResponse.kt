package bes.max.mobinttest.companies.data.dto

import bes.max.mobinttest.companies.domain.models.CompanySearchResult
import bes.max.mobinttest.core.data.dto.Response

data class GetAllCompaniesResponse(
    val companies: List<CompanyDto>,
    val limit: Int,
    val offset: Int
) : Response()

fun GetAllCompaniesResponse.map(): CompanySearchResult = CompanySearchResult(
    companies = companies.map { it.map() },
    limit = limit,
    offset = offset
)