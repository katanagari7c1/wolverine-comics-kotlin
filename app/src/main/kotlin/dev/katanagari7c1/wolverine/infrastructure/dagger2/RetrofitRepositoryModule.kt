package dev.katanagari7c1.wolverine.infrastructure.dagger2

import dagger.Module
import dagger.Provides
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitAuthenticationParametersFactory
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitComicRepository
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitFactory

@Module
class RetrofitRepositoryModule {

	@Provides
	fun providesComicRepository(
		retrofitFactory: RetrofitFactory,
		retrofitAuthenticationParametersFactory: RetrofitAuthenticationParametersFactory
	): ComicRepository {
		return RetrofitComicRepository(retrofitFactory, retrofitAuthenticationParametersFactory)
	}
}