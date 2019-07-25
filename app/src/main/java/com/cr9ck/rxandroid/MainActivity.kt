package com.cr9ck.rxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
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
}
