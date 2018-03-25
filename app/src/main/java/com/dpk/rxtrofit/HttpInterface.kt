package com.dpk.rxtrofit

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by AlphaDog on 2018/3/25.
 */
interface HttpInterface {
    @GET("top250")
    fun getTopMovie(@Query("start") start: Int, @Query("count") count: Int): Observable<GetMovie<List<Subject>>>
}