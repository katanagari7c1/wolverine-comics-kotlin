package dev.katanagari7c1.wolverine.infrastructure

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository


class MockComicRepository:ComicRepository {

	val comics:List<Comic> = listOf(
		Comic(
			title = "X-Men: Days of Future Past (Trade Paperback)",
			description = "Re-live the legendary first journey into the dystopian future of 2013 - where Sentinels stalk the Earth, and the X-Men are humanity's only hope...until they die!",
			thumbnail = "",
			images = listOf()
		),
		Comic(
			title = "Official Handbook of the Marvel Universe (2004) #10 (MARVEL KNIGHTS)",
			description = "On the mean streets of the Marvel Universe, the kid gloves come off. Guardian devils, vengeance-seeking vigilantes and enigmatic assassins stalk the city's dark underbelly _ and the urban action unfolds with gritty intensity. The newest entry in Marvel's best-selling Handbook series, OHOTMUMK04 includes in-depth bios on a host of the House's edgiest icons - from Black Panther to Shang-Chi!",
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

	override fun findAll(): List<Comic> {
		return this.comics
	}

	override fun findByTitle(title:String):Comic? {
		this.comics.forEach { comic ->
			if (comic.title == title) {
				return comic
			}
		}

		return null
	}
}