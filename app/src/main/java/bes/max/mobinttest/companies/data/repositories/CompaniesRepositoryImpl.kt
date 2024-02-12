package bes.max.mobinttest.companies.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import bes.max.mobinttest.companies.data.database.CompanyDao
import bes.max.mobinttest.companies.data.database.CompanyEntity
import bes.max.mobinttest.companies.data.dto.GetAllCompaniesRequest
import bes.max.mobinttest.companies.data.dto.GetAllCompaniesResponse
import bes.max.mobinttest.companies.data.dto.mapToEntity
import bes.max.mobinttest.core.data.network.NetworkClient
import bes.max.mobinttest.core.data.network.RetrofitNetworkClient.Companion.CODE_SUCCESS
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CompaniesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val companyDao: CompanyDao
) : RemoteMediator<Int, CompanyEntity>() {

    private var nextPage = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CompanyEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    nextPage = 1
                    nextPage
                }

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        nextPage = 1
                        nextPage
                    } else {
                        nextPage += 1
                        nextPage
                    }
                }
            }

            val response = networkClient.doRequest(
                GetAllCompaniesRequest(
                    offset = loadKey,
                    limit = state.config.pageSize
                )
            )
            if (response.resultCode == CODE_SUCCESS) {
                if (loadType == LoadType.REFRESH) {
                    companyDao.clearAll()
                }
                val entities =
                    (response as GetAllCompaniesResponse).companies.map { it.mapToEntity() }
                companyDao.insertsCompanies(entities)

                MediatorResult.Success(
                    endOfPaginationReached = response.companies.isEmpty()
                )
            } else {
                MediatorResult.Error(HttpException(response.response as Response<*>))
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}