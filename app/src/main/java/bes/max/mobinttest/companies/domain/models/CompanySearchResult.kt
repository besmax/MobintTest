package bes.max.mobinttest.companies.domain.models

data class CompanySearchResult(
    val companies: List<Company>,
    val limit: Int,
    val offset: Int
)
