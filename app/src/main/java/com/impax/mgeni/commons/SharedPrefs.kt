package com.impax.mgeni.commons

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(private val context: Context)
{
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("iTrack", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun getItem(name: String?): String? {
        return if (sharedPreferences.getString(name, "")!!.trim { it <= ' ' }.isNotEmpty()) {
            sharedPreferences.getString(name, "")
        } else {
            ""
        }
    }

    fun putItem(name: String?, value: Int): Boolean {
        editor.putInt(name, value)
        return editor.commit()
    }

    fun putItem(name: String?, value: Float): Boolean {
        editor.putFloat(name, value)
        return editor.commit()
    }

    fun getItemInt(name: String?): Int {
        return sharedPreferences.getInt(name, 0)
    }

    fun getItemDouble(name: String?): Float {
        return sharedPreferences.getFloat(name, 0f)
    }

    fun getItemBoolean(name: String?): Boolean {
        return sharedPreferences.getBoolean(name, false)
    }

    fun putItem(name: String?, value: Boolean): Boolean {
        if (sharedPreferences.getBoolean(name, false)) {
            editor.remove(name)
            editor.putBoolean(name, value)
        } else {
            editor.putBoolean(name, value)
        }
        return editor.commit()
    }

    fun putItem(name: String?, value: String?): Boolean {
        if (sharedPreferences.getString(name, "") == "" || sharedPreferences.getString(
                name,
                null
            ) == null
        ) {
            editor.putString(name, value)
        } else {
            editor.remove(name)
            editor.putString(name, value)
        }
        return editor.commit()
    }

    fun removeItem(name: String?): Boolean {
        editor.remove(name)
        return editor.commit()
    }

    fun clearSharePref() {
        editor.clear()
    }
}