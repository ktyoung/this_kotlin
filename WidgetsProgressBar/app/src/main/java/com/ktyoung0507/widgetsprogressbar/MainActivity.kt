package com.ktyoung0507.widgetsprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ktyoung0507.widgetsprogressbar.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        thread(start=true) {
            Thread.sleep(300000)
            runOnUiThread {
                showProgress(false)
            }
        }
    }

    fun showProgress(show: Boolean) {
       binding.progressBar.visibility = if(show) View.VISIBLE else View.GONE
    }
}