package com.cr9ck.rxandroid

import android.os.Bundle
import android.util.Log
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
        map()
        filter()
        subscribeOn()
        errorHandling()
    }

    private fun initListener() {
        RxView.clicks(button)
            .map { increment1() }
            .throttleFirst(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                increment2()
            }
    }

    private fun increment1() {
        var counter = counter1.text.toString().toInt()
        counter++
        counter1.text = counter.toString()
    }

    private fun increment2() {
        var counter = counter2.text.toString().toInt()
        counter++
        counter2.text = counter.toString()
    }

    private fun map() {
        Observable.just(1, 3, 5, 7)
            .map { number ->
                number * 2
            }
            .subscribe {
                Log.i("map", it.toString())
            }
    }

    private fun filter() {
        Observable.just(1, 3, 4, 5)
//            .filter{it>3}
            .filter { it -> it > 3 }
            .subscribe {
                Log.i("filter", it.toString())
            }
    }

    private fun subscribeOn() {
//        addDisposable(
//        networkService                       // io Thread
//            .getData()                       // io Thread
//            .subscribeOn(Schedulers.io())    // change Thread
//            .observeOn(Schedulers.computation()) // change Thread
//            .map {                               // comp thread
//                doSomething()                    // comp thread
//            }                                    // comp thread
//            .observeOn(AndroidSchedulers.mainThread()) // change Thread
//            .subscribe{                                // ui thread
//                updateView()                           // ui thread
//            }                                          // ui thread
//          )
    }

    private fun errorHandling() {
        getSingle()
            .doOnError { it ->
                // do smthng
            }
            .subscribe(
                { it ->
                    {
                        // handle onSucces
                    }
                },
                { it ->
                    // handle onError
                }
            )
    }

    private fun getSingle(): Single<Int> = Single.just(1)

}
