package com.seifmortada.applications.suprema

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seifmortada.applications.suprema.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // مسح bs-session-id عند بدء التطبيق
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("bs-session-id") // إزالة الجلسة السابقة
        editor.apply()
    }
}



