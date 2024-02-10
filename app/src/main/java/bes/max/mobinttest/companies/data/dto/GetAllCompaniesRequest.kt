package bes.max.mobinttest.companies.data.dto

private const val OFFSET = "offset"
private const val LIMIT = "limit"


data class GetAllCompaniesRequest(
    val offset: Int = 0,
    val limit: Int = 5
)

fun GetAllCompaniesRequest.toQueryMap(): Map<String, String> = buildMap {
    offset.also { put(OFFSET, it.toString()) }
    limit.also { put(LIMIT, it.toString()) }
}