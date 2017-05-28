package dev.katanagari7c1.wolverine.domain.use_case

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository

class ComicFindByIdUseCase(val repository: ComicRepository) {

	fun execute(comicId:String):Comic? {
		return this.repository.findById(comicId)
	}
}
