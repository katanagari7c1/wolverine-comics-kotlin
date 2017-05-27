package dev.katanagari7c1.wolverine.domain.use_case

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository


class ComicFindAllUseCase(val repository: ComicRepository) {

	fun execute():List<Comic> {
		return this.repository.findAll()
	}

}