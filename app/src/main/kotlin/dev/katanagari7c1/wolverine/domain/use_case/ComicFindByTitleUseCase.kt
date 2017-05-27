package dev.katanagari7c1.wolverine.domain.use_case

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository

class ComicFindByTitleUseCase(val repository: ComicRepository) {

	fun execute(title:String):Comic? {
		return this.repository.findByTitle(title)
	}
}
