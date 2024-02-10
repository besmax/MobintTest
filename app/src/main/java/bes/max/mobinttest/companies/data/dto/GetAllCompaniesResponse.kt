package bes.max.mobinttest.companies.data.dto

import bes.max.mobinttest.core.data.dto.Response

data class GetAllCompaniesResponse(
    val companies: List<CompanyDto>,
    val limit: Int,
    val offser: Int
) : Response()