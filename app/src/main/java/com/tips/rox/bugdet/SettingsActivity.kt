package com.tips.rox.bugdet

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceActivity


/**
 * Created by ralibert-ext on 03/01/2018.
 */
class SettingsActivity : PreferenceActivity(), SharedPreferences.OnSharedPreferenceChangeListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
    }

    override fun onSharedPreferenceChanged(sharedPref: SharedPreferences?, key: String?) {
        if(key.equals(getString(R.string.preference_budget_total_key))){
            val editor : SharedPreferences.Editor = sharedPref!!.edit()
            editor.putInt(getString(R.string.preference_budget_total_key), 0)
            editor.commit()
        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}