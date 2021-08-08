package com.example.mbregistrationapp.ui.activity

import androidx.fragment.app.Fragment
import com.example.mbregistrationapp.R
import com.example.mbregistrationapp.base.BaseActivity
import com.example.mbregistrationapp.databinding.ActivityMainBinding
import com.example.mbregistrationapp.storage.AppPreferences
import com.example.mbregistrationapp.ui.fragment.main.MainFragment
import com.example.mbregistrationapp.ui.fragment.registration.RegistrationFragment

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        if (checkUser()) {
            replaceFragment(MainFragment.newInstance())
        } else {
            replaceFragment(RegistrationFragment.newInstance())
        }
    }

    private fun checkUser(): Boolean {
        return AppPreferences.authToken?.isNotEmpty() ?: false
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}