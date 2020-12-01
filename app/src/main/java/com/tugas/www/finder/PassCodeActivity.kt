package com.tugas.www.finder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tugas.www.finder.setting.SettingPreference
import kotlinx.android.synthetic.main.activity_pass_code.*
import kotlinx.android.synthetic.main.activity_set_pass_code.*
import kotlinx.android.synthetic.main.activity_set_pass_code.et_pass_code

class PassCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_code)

        val settingPreference = SettingPreference(this)

        btn_confirm_pass_code.setOnClickListener {
            if (et_pass_code.text!!.length >= 4)   {
                val passCode = et_pass_code.text.toString()
                val savePassCode = settingPreference.getPassCodeValue()
                if (passCode.toInt() == savePassCode) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Wrong PassCode", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Insert 4 numbers", Toast.LENGTH_SHORT).show()
            }
        }
    }
}