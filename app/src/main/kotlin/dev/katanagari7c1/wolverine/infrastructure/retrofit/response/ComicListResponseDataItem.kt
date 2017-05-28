package dev.katanagari7c1.wolverine.infrastructure.retrofit.response

import dev.katanagari7c1.wolverine.domain.entity.Comic


data class ComicListResponseDataItem(
	val id:String,
	val title:String,
	val description:String?,
	val thumbnail:ComicImage,
	val images:List<ComicImage>
) {
	fun toComic():Comic {
		return Comic(
			comicId = this.id,
			title = this.title,
			description = this.description ?: "",
			thumbnail = thumbnail.fullPath(),
			images = this.images.map { image -> image.fullPath() }
		)
	}
}