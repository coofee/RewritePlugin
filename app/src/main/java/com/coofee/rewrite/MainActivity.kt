package com.coofee.rewrite

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.coofee.rewrite.hook.sharedpreferences.ShadowSharedPreferences
import com.coofee.rewrite.hook.sharedpreferences.SharedPreferencesProxy

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test_Context_getSharedPreferences_string()

        test_Activity_getSharedPreferences_int()

        test_PreferenceManager_getSharedPreferences_string()

        SharedPreferencesProxy.print()
    }

    private fun test_Context_getSharedPreferences_string() {
        getSharedPreferences("sp_context", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("test_context_getSharedPreferences_string", true)
            .commit()
    }

    private fun test_Activity_getSharedPreferences_int() {
        getPreferences(Context.MODE_PRIVATE)
            .edit()
            .putBoolean("test_Activity_getSharedPreferences_int", true)
            .commit()
    }

    private fun test_PreferenceManager_getSharedPreferences_string() {
        PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putBoolean("test_PreferenceManager_getSharedPreferences_string", true)
            .commit()

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            val defaultSharedPreferencesName = PreferenceManager.getDefaultSharedPreferencesName(this)
        }
    }

    private fun test_shadow_preferences() {
        ShadowSharedPreferences.getSharedPreferences(this, "shadow", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("test_shadow_preferences", true)
            .commit()
    }
}