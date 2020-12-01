package com.tugas.www.finder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.tugas.www.finder.setting.SettingPreference

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val settingPreference = SettingPreference(this)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if (settingPreference.getPassCodeState()) {
                val intent = Intent(this@SplashActivity, PassCodeActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 2000)
    }
}