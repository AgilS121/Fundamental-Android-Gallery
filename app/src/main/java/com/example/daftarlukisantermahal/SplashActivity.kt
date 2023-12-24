package com.example.daftarlukisantermahal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intentMove = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intentMove)
            finish()
        }, SPLASH_TIME)
    }
}