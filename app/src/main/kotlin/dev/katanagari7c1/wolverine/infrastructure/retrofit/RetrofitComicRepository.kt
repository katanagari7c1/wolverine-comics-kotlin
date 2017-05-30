package dev.katanagari7c1.wolverine.infrastructure.retrofit

import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.error.FetchError
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository
import javax.inject.Inject

class RetrofitComicRepository @Inject constructor(
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

	private fun requestComics(parameters:MutableMap<String,String>):List<Comic> {
		parameters.put("orderBy", "-focDate")

		try {
			val apiResponse = this.comicApi.findAllComics(parameters).execute()

			if (apiResponse.code() == 200) {
				val response = apiResponse.body()

				if (response != null) {
					return response.data.results.map { data -> data.toComic() }
				}
				else {
					return listOf()
				}
			}
			else {
				throw FetchError("Retrofit request failed with code ${apiResponse.code()}")
			}
		}
		catch (exception:Exception) {
			throw FetchError("A connection error ocurred")
		}

	}
}