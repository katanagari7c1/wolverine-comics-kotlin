package dev.katanagari7c1.wolverine.presentation.main.data_loader

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindFromOffsetUseCase
import dev.katanagari7c1.wolverine.domain.use_case.ComicSaveOrUpdateUseCase

class ComicListDataLoader (
	val loadWithOffsetUseCase: ComicFindFromOffsetUseCase, val saveUseCase: ComicSaveOrUpdateUseCase
) {

	val numberOfItemsToLoad = 50
	private var offset = 0
	private var endReached = false

	fun load():List<Comic> {
		if (!endReached) {
			val comics = this.loadWithOffsetUseCase.execute(this.offset, this.numberOfItemsToLoad)
			this.saveUseCase.execute(comics)
			this.offset += comics.size
			this.endReached = comics.isEmpty()
			return comics
		}

		return listOf()
	}

}