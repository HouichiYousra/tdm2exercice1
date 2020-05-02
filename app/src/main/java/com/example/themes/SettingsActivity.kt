package com.example.themes


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.internal.ContextUtils.getActivity
import dev.sasikanth.colorsheet.ColorSheet


class SettingsActivity : AppCompatActivity() {


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


        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        val button3 = findViewById<Button>(R.id.button3)
        colors= resources.getIntArray(R.array.colors)
        button3.setOnClickListener{
            ColorSheet().colorPicker(
                colors = colors!!,
                noColorOption = true,
                listener = { color ->
                    val editor = prefs!!.edit()
                    editor.putInt("app_color",color)
                    editor.apply()


                    val goMain = Intent(this, MainActivity::class.java)
                    startActivity(goMain)

                })
                .show(supportFragmentManager)
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}