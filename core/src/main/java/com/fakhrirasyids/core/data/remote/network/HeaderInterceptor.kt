package com.fakhrirasyids.core.data.remote.network

import com.fakhrirasyids.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Authorization", "Token " + BuildConfig.API_KEY)
            .build()
        return chain.proceed(request)
    }
}