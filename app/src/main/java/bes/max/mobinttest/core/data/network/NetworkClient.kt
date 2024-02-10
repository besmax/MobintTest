package bes.max.mobinttest.core.data.network

import bes.max.mobinttest.core.data.dto.Response


interface NetworkClient {

    suspend fun doRequest(request: Any) : Response

}