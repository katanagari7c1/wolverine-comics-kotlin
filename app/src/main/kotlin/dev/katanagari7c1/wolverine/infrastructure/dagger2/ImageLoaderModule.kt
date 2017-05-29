package dev.katanagari7c1.wolverine.infrastructure.dagger2

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.katanagari7c1.wolverine.domain.util.ImageLoader
import dev.katanagari7c1.wolverine.infrastructure.glide.GlideImageLoader

@Module
class ImageLoaderModule {

	@Provides
	fun provideImageLoader(context: Context):ImageLoader {
		return GlideImageLoader(context)
	}
}