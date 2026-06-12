package com.raqun.android.binding

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import com.raqun.android.extensions.loadImage

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        imageView.loadImage(url)
    }
}
