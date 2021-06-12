package com.example.mvvmsamplewithhiltandcoroutine.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object GlideUtils {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(imageView: ImageView,imageUrl : String){
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}