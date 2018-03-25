package com.dpk.rxtrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by AlphaDog on 2018/3/25.
 */
class HttpMethod {

    private var okclient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .addNetworkInterceptor(OAuthIntercepter())
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

    private var retrofit: Retrofit = Retrofit.Builder()
            .client(okclient)
            .baseUrl("https://api.douban.com/v2/movie/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().setLenient().create()))
            .build()

    private var httpInterface: HttpInterface = retrofit.create(HttpInterface::class.java)

    companion object {
        val httpMethod: HttpMethod = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = HttpMethod()
    }

    fun getTopMovie(start: Int, count: Int, subscriber: Subscriber<List<Subject>>) {
        toSubscriber(httpInterface.getTopMovie(start, count), subscriber)
    }

    private fun <T> toSubscriber(observable: Observable<GetMovie<T>>, subscriber: Subscriber<T>) {
        observable
                .compose {
                    it.flatMap { result ->
                        if (result.count!! > 0)
                            createData(result.subjects)
                        else
                            Observable.error(Throwable(""))
                    }
                }
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {}
                .subscribe(subscriber)
    }

    private fun <T> createData(data: T): Observable<T> {
        return Observable.create { subscriber ->
            try {
                subscriber.onNext(data)
                subscriber.onCompleted()
            } catch (e: Exception) {
                subscriber.onError(e)
            }
        }
    }
}