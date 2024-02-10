package bes.max.mobinttest.companies.data.repositories

import bes.max.mobinttest.companies.domain.models.Company
import bes.max.mobinttest.companies.domain.repositories.CompaniesRepository
import bes.max.mobinttest.core.data.network.NetworkClient
import bes.max.mobinttest.core.util.Resource
import kotlinx.coroutines.flow.Flow

class CompaniesRepositoryImpl(
    networkClient: NetworkClient
): CompaniesRepository {
    override suspend fun getCompanies(): Flow<Resource<List<Company>>> {
        TODO("Not yet implemented")
    }
}