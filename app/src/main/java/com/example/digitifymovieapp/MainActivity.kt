package com.example.digitifymovieapp

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var showContent = false

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to keep splash screen on the screen for a long period
        val content = findViewById<View>(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                if (showContent) {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                }
                showContentAfterTime()
                return false
            }
        })
    }

    private fun showContentAfterTime() {
        Handler().postDelayed({ showContent = true }, 1000)
    }
}