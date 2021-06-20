package com.mobikasa.androidassignment

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


class Utils {
    companion object {
        fun hideKeyboard(activity: Activity) {
            val view: View = activity.findViewById(android.R.id.content)
            val imm: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}