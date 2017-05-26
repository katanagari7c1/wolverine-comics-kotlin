package dev.katanagari7c1.wolverine.infrastructure

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository


class MockComicRepository:ComicRepository {

	override fun findAll(): List<Comic> {
		return listOf(
			Comic(
				title = "X-Men: Days of Future Past (Trade Paperback)",
				description = "",
				thumbnail = "",
				images = listOf()
			),
			Comic(
				title = "Official Handbook of the Marvel Universe (2004) #10 (MARVEL KNIGHTS)",
				description = "",
				thumbnail = "",
				images = listOf()
			),
			Comic(
				title = "X-Men: Phoenix - Warsong (2006)",
				description = "",
				thumbnail = "",
				images = listOf()
			)
		)
	}
}