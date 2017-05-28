package dev.katanagari7c1.wolverine.infrastructure.retrofit

import dev.katanagari7c1.wolverine.BuildConfig
import dev.katanagari7c1.wolverine.infrastructure.retrofit.util.AuthorizationKeyGenerator
import java.util.*


class RetrofitAuthenticationParametersFactory(val authorizationKeyGenerator: AuthorizationKeyGenerator) {

	fun getParameters(): MutableMap<String, String> {
		val timestamp = "${Date().time / 1000}"
		val hash = this.authorizationKeyGenerator.generate(
			timestamp,
			BuildConfig.MARVEL_API_PUBLIC_KEY,
			BuildConfig.MARVEL_API_PRIVATE_KEY
		)

		return mutableMapOf(
			"ts" to timestamp,
			"apikey" to BuildConfig.MARVEL_API_PUBLIC_KEY,
			"hash" to hash
		)
	}
}