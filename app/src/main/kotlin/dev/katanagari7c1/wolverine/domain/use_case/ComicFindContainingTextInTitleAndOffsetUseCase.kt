package dev.katanagari7c1.wolverine.domain.use_case

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository

class ComicFindContainingTextInTitleAndOffsetUseCase(
	val query:String,
	val repository:ComicRepository
):ComicFindFromOffsetUseCase {

	override fun execute(offset:Int, limit:Int):List<Comic> {
		return this.repository.findWithTitleQueryAndOffset(query, offset, limit)
	}
}

