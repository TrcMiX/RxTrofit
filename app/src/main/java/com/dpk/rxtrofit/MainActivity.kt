package com.dpk.rxtrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscriber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        HttpMethod.httpMethod.getTopMovie(1,1,object :Subscriber<List<Subject>>(){
            override fun onNext(t: List<Subject>?) {
                sample_text.text =t?.get(0)?.originalTitle
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }

        })

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
