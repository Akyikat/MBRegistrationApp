package com.example.mbregistrationapp.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Fragment.replaceFragmentExt(
    @IdRes containerId: Int,
    fragment: Fragment,
    backStack: Boolean = true
) {
    requireActivity().supportFragmentManager.commit {
        if (backStack) {
            replace(containerId, fragment).addToBackStack(null)
        } else
            replace(containerId, fragment)
    }
}
