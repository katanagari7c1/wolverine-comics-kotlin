package dev.katanagari7c1.wolverine.domain.repository

import dev.katanagari7c1.wolverine.domain.entity.Comic


interface ComicPersistanceRepository {
	fun insertOrUpdate(comics:List<Comic>)
}