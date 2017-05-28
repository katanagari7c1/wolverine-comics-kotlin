package dev.katanagari7c1.wolverine.presentation.main

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindFromOffsetUseCase


class ComicListDataLoader(val loadWithOffsetUseCase: ComicFindFromOffsetUseCase) {

	val numberOfItemsToLoad = 50
	private var offset = 0
	private var endReached = false

	fun load():List<Comic> {
		if (!endReached) {
			val comics = this.loadWithOffsetUseCase.execute(this.offset, this.numberOfItemsToLoad)
			this.offset += comics.size
			this.endReached = comics.isEmpty()
			return comics
		}

		return listOf()
	}
}