package com.itw.todolistkotlinapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.itw.todolistkotlinapp.R
import com.itw.todolistkotlinapp.utils.ToDoPref

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000 // 5 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            if (ToDoPref.getUserId != 0) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                startActivity(Intent(this, RegisterActivity::class.java))
            }

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)

    }
}