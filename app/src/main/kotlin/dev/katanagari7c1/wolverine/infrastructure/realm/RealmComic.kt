package dev.katanagari7c1.wolverine.infrastructure.realm

import dev.katanagari7c1.wolverine.domain.entity.Comic
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmComic(
	@PrimaryKey var comicId:String,
	var title:String = "",
	var description:String = "",
	var thumbnail:String = "",
	var images:RealmList<RealmImageListItem> = RealmList(),
	var numPages:String = "0",
	var series:String = ""
): RealmObject() {

	constructor(comic:Comic) : this(
		comicId = comic.comicId,
		title = comic.title,
		description = comic.description,
		thumbnail = comic.thumbnail,
		images = RealmList<RealmImageListItem>(),
		numPages = comic.numPages,
		series = comic.series
	) {
		val convertedImages = comic.images.map { image -> RealmImageListItem(image) }
		this.images.addAll(convertedImages)
	}

	constructor(): this(
		comicId = ""
	)

	fun toComic():Comic {
		val imageList = images.map { image -> image.path }
		return Comic(comicId, title, description, thumbnail, imageList, numPages, series)
	}
}