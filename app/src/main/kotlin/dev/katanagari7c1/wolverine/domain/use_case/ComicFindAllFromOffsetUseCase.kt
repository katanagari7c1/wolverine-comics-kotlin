package dev.katanagari7c1.wolverine.domain.use_case

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository


class ComicFindAllFromOffsetUseCase(val repository: ComicRepository):ComicFindFromOffsetUseCase {

	override fun execute(offset: Int, limit: Int):List<Comic> {
		return this.repository.findFromOffsetAndLimit(offset, limit)
	}

}