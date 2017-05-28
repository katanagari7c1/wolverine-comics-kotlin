package dev.katanagari7c1.wolverine.domain.use_case

import dev.katanagari7c1.wolverine.domain.entity.Comic


interface ComicFindFromOffsetUseCase {
	fun execute(offset: Int, limit: Int):List<Comic>
}