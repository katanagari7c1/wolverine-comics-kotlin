package dev.katanagari7c1.wolverine.domain.util

import android.widget.ImageView

interface ImageLoader {
	fun loadImageInto(imageUrl:String, imageView:ImageView)
}