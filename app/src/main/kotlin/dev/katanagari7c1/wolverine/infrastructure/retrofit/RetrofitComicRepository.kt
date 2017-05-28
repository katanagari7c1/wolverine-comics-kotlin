package dev.katanagari7c1.wolverine.infrastructure.retrofit

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository

class RetrofitComicRepository(
	retrofitFactory: RetrofitFactory,
	val parametersFactory: RetrofitAuthenticationParametersFactory
):ComicRepository {

	val comicApi:MarvelComicApi = retrofitFactory.create().create(MarvelComicApi::class.java)

	override fun findAll(): List<Comic> {
		val parameters = this.parametersFactory.getParameters()

		return this.requestComics(parameters)
	}

	override fun findById(comicId: String): Comic? {
		val parameters = this.parametersFactory.getParameters()
		val response = this.comicApi.findComicDetails(comicId, parameters).execute().body()

		if (response != null) {
			return response.data.results[0].toComic()
		}

		return null
	}

	override fun findFromOffsetAndLimit(offset: Int, limit: Int): List<Comic> {
		val parameters = this.parametersFactory.getParameters()
		parameters.put("offset", "$offset")
		parameters.put("limit", "$limit")

		return this.requestComics(parameters)
	}

	override fun findWithTitleQueryAndOffset(query: String, offset: Int, limit: Int): List<Comic> {
		val parameters = this.parametersFactory.getParameters()
		parameters.put("offset", "$offset")
		parameters.put("limit", "$limit")
		parameters.put("titleStartsWith", query)

		return this.requestComics(parameters)
	}

	private fun requestComics(parameters:Map<String,String>):List<Comic> {
		val apiResponse = this.comicApi.findAllComics(parameters).execute()
		val response = apiResponse.body()

		if (response != null) {
			return response.data.results.map { data -> data.toComic() }
		}
		else {
			return listOf()
		}
	}
}