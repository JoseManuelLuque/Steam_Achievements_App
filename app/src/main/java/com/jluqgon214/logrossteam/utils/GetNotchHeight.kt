package com.jluqgon214.logrossteam.utils

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Composable
fun getStatusBarHeight(): Int {
    val view = LocalView.current
    var statusBarHeight = 0

    ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
        statusBarHeight = insets.systemWindowInsetTop
        insets
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowInsets = view.rootWindowInsets
        val insets = WindowInsetsCompat.toWindowInsetsCompat(windowInsets)
        statusBarHeight = insets.systemWindowInsetTop
    }

    return statusBarHeight
}