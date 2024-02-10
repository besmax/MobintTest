package bes.max.mobinttest.companies.domain.repositories

import bes.max.mobinttest.companies.domain.models.Company
import bes.max.mobinttest.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface CompaniesRepository {

    suspend fun getCompanies(): Flow<Resource<List<Company>>>
}