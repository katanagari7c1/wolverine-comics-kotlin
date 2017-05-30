package dev.katanagari7c1.wolverine.infrastructure.dagger2

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.katanagari7c1.wolverine.domain.repository.ComicPersistanceRepository
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository
import dev.katanagari7c1.wolverine.infrastructure.realm.RealmComicPersistenceRepository
import dev.katanagari7c1.wolverine.infrastructure.realm.RealmComicRepository
import dev.katanagari7c1.wolverine.infrastructure.realm.RealmHelper
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitAuthenticationParametersFactory
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitComicRepository
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitFactory
import javax.inject.Named

@Module
class RepositoryModule {

	@Provides @Named("network")
	fun providesRetrofitComicRepository(
		retrofitFactory: RetrofitFactory,
		retrofitAuthenticationParametersFactory: RetrofitAuthenticationParametersFactory
	): ComicRepository {
		return RetrofitComicRepository(retrofitFactory, retrofitAuthenticationParametersFactory)
	}

	@Provides @Named("persistence")
	fun providesRealmComicRepository(
		realmHelper: RealmHelper,
		context: Context
	): ComicRepository {
		return RealmComicRepository(realmHelper, context)
	}

	@Provides
	fun providesComicPersistenceRepository(realmHelper: RealmHelper, context: Context): ComicPersistanceRepository {
		return RealmComicPersistenceRepository(realmHelper, context)
	}
}