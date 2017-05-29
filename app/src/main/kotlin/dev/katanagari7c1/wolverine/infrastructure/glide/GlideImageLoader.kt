package dev.katanagari7c1.wolverine.infrastructure.glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dev.katanagari7c1.wolverine.domain.util.ImageLoader
import javax.inject.Inject


class GlideImageLoader @Inject constructor(context: Context): ImageLoader {

	val glideInstance: RequestManager = Glide.with(context)

	override fun loadImageInto(imageUrl: String, imageView: ImageView) {
		this.glideInstance.load(imageUrl).into(imageView)
	}
}