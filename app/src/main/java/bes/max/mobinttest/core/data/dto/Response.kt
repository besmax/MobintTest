package bes.max.mobinttest.core.data.dto

import retrofit2.Response

open class Response {
    var resultCode = 0
    var exception: Throwable? = null
    var response: Any? = null
}