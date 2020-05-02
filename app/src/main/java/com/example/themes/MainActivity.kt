package com.example.themes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var prefs : SharedPreferences?= null
    var colors: IntArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        colors = resources.getIntArray(R.array.colors)
        prefs = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val color = prefs!!.getInt("app_color", colors!![0])
        when(color){
            colors!![0] -> setTheme(R.style.AppTheme2)
            colors!![1] -> setTheme(R.style.AppTheme3)
            colors!![2] -> setTheme(R.style.AppTheme4)
            colors!![3] -> setTheme(R.style.AppTheme5)

        }
        setContentView(R.layout.activity_main)
        val button : Button
        val button2 : Button
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button.setOnClickListener{
            val goSettings = Intent(this, SettingsActivity::class.java)
            startActivity(goSettings)
        }
        button2.setOnClickListener{
            val goList = Intent(this, ItemListActivity::class.java)
            startActivity(goList)
        }
    }

}
