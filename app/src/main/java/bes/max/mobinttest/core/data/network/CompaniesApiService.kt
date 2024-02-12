package bes.max.mobinttest.core.data.network

import bes.max.mobinttest.companies.data.dto.GetAllCompaniesRequest
import bes.max.mobinttest.companies.data.dto.GetAllCompaniesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface CompaniesApiService {

    @POST("getAllCompanies")
    suspend fun getAllCompanies(@Body request: GetAllCompaniesRequest): Response<GetAllCompaniesResponse>


}