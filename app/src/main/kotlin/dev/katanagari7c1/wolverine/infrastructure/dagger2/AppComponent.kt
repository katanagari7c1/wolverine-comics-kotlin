package dev.katanagari7c1.wolverine.infrastructure.dagger2

import dagger.Component
import dev.katanagari7c1.wolverine.presentation.detail.DetailActivity
import dev.katanagari7c1.wolverine.presentation.main.MainActivity
import dev.katanagari7c1.wolverine.presentation.search_result.SearchResultActivity
import javax.inject.Singleton

@Component(modules = arrayOf(
	RepositoryModule::class,
	ImageLoaderModule::class,
	ActivityModule::class
	)
)
@Singleton
interface AppComponent {
	fun inject(activity: MainActivity)
	fun inject(activity: SearchResultActivity)
	fun inject(activity: DetailActivity)
}