package dev.katanagari7c1.wolverine.domain.repository

import dev.katanagari7c1.wolverine.domain.entity.Comic


interface ComicRepository {
	fun findAll():List<Comic>
}