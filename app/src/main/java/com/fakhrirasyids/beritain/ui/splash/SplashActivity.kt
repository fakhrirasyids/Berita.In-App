package com.fakhrirasyids.beritain.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.fakhrirasyids.beritain.ui.main.MainActivity
import com.fakhrirasyids.beritain.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val iMain = Intent(this, MainActivity::class.java)
            iMain.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(iMain)
            finish()
        }, SPLASH_TIME_OUT)
    }

    companion object {
        const val SPLASH_TIME_OUT: Long = 1500
    }
}