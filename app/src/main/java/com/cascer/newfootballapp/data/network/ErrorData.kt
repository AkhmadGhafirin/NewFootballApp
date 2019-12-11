package com.cascer.newfootballapp.data.network

class ErrorData(
    var message: String = "",
    var errorCode: String = "",
    var throwable: Throwable? = null
)