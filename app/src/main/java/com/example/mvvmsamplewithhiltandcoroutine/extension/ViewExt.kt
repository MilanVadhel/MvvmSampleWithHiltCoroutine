package com.example.mvvmsamplewithhiltandcoroutine.extension

import android.view.View

fun View.show() {
    if (visibility == View.INVISIBLE) {
        this.visibility = View.VISIBLE
    }
}

fun View.hide() {
    if (visibility == View.VISIBLE) {
        this.visibility = View.INVISIBLE
    }
}
