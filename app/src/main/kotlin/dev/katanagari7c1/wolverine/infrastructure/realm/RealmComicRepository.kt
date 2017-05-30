package dev.katanagari7c1.wolverine.infrastructure.realm

import android.content.Context
import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository
import javax.inject.Inject

class RealmComicRepository @Inject constructor (val realmHelper: RealmHelper, val context: Context):ComicRepository {

	override fun findAll(): List<Comic> {
		val realm = this.realmHelper.getRealmInstance()
		return realm.where(RealmComic::class.java).findAll().map { realmComic -> realmComic.toComic() }
	}

	override fun findById(comicId: String): Comic? {
		val realm = this.realmHelper.getRealmInstance()
		return realm.where(RealmComic::class.java).equalTo("comicId", comicId).findFirst().toComic()
	}

	override fun findFromOffsetAndLimit(offset: Int, limit: Int): List<Comic> {
		val realm = this.realmHelper.getRealmInstance()
		val allItems = realm.where(RealmComic::class.java).findAll()
		return allItems.subList(offset, offset + limit).map { realmComic -> realmComic.toComic() }
	}

	override fun findWithTitleQueryAndOffset(query: String, offset: Int, limit: Int): List<Comic> {
		val realm = this.realmHelper.getRealmInstance()
		val allItems = realm.where(RealmComic::class.java).beginsWith("title", query).findAll()
		return allItems.subList(offset, offset + limit).map { realmComic -> realmComic.toComic() }
	}

}
