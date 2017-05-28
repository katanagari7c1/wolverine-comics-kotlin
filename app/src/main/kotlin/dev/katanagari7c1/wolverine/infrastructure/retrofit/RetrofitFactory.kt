package dev.katanagari7c1.wolverine.infrastructure.retrofit

import dev.katanagari7c1.wolverine.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitFactory {

	fun create():Retrofit {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.MARVEL_API_SERVER)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
}