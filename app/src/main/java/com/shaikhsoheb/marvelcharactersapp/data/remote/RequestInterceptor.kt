package com.shaikhsoheb.marvelcharactersapp.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * An interceptor which add query parameters: public key, ts and hash into base url.
 */
@Singleton
class RequestInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val publicKey = MarvelApiConstants.API_PUBLIC_KEY
        val ts = MarvelApiConstants.API_TS
        val hash = MarvelApiConstants.API_HASH

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", hash)
            .build()

        val requestBuilder = original.newBuilder().url(url)

        return chain.proceed(requestBuilder.build())
    }
}