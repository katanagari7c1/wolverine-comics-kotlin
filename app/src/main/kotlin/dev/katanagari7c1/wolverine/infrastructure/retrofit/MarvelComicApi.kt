package dev.katanagari7c1.wolverine.infrastructure.retrofit

import dev.katanagari7c1.wolverine.infrastructure.retrofit.response.ComicListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface MarvelComicApi {

	@GET("v1/public/characters/1009718/comics")
	fun findAllComics(@QueryMap parameters:Map<String,String>): Call<ComicListResponse>

	@GET("v1/public/comics/{comicId}")
	fun findComicDetails(@Path("comicId")comidId:String, @QueryMap parameters:Map<String,String>): Call<ComicListResponse?>


}