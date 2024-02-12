package bes.max.mobinttest.core.data.network

import android.content.Context
import bes.max.mobinttest.companies.data.dto.GetAllCompaniesRequest
import bes.max.mobinttest.core.data.dto.Response

class RetrofitNetworkClient(
    private val context: Context,
    private val companiesApiService: CompaniesApiService
) : NetworkClient {
    override suspend fun doRequest(request: Any): Response {
        if (!ConnectionChecker.isConnected(context)) {
            return Response().apply { resultCode = CODE_NO_INTERNET }
        }

        return when (request) {
            is GetAllCompaniesRequest -> getCompanies(request)
            else -> Response().apply {
                resultCode = CODE_WRONG_REQUEST
            }

        }
    }

    private suspend fun getCompanies(request: GetAllCompaniesRequest): Response {
        return try {
            val response = companiesApiService.getAllCompanies(request)
            if (response.code() == CODE_SUCCESS && response.body() != null) {
                response.body()!!.apply { resultCode = CODE_SUCCESS }
            } else {
                Response().apply {
                    resultCode = response.code()
                    this.response = response
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Response().apply {
                resultCode = CODE_SERVER_ERROR
                exception = e
            }
        }
    }

    companion object {
        const val CODE_NO_INTERNET = -1
        const val CODE_SUCCESS = 200
        const val CODE_WRONG_REQUEST = 400
        const val CODE_SERVER_ERROR = 500
    }
}