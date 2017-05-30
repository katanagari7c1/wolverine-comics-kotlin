package dev.katanagari7c1.wolverine.infrastructure.realm

import android.content.Context
import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicPersistanceRepository

class RealmComicPersistenceRepository(val realmHelper: RealmHelper, val context: Context): ComicPersistanceRepository {

	override fun insertOrUpdate(comics: List<Comic>) {
		val realm = this.realmHelper.getRealmInstance()
		realm.beginTransaction()
		comics.forEach { comic ->
			realm.copyToRealmOrUpdate(RealmComic(comic))
		}
		realm.commitTransaction()
	}
}