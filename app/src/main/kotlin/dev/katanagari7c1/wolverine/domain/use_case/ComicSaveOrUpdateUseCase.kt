package dev.katanagari7c1.wolverine.domain.use_case

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicPersistanceRepository
import javax.inject.Inject


class ComicSaveOrUpdateUseCase @Inject constructor(val repository:ComicPersistanceRepository ) {

	fun execute(comics: List<Comic>) {
		this.repository.insertOrUpdate(comics)
	}
}