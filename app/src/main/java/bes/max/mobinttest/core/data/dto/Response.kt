package bes.max.mobinttest.core.data.dto

open class Response {
    var resultCode = 0
    var exception: Throwable? = null
    var response: Any? = null
}