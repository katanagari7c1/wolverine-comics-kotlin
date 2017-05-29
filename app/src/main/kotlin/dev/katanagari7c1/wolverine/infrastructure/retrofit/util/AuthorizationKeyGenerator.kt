package dev.katanagari7c1.wolverine.infrastructure.retrofit.util

import dev.katanagari7c1.wolverine.domain.extension.md5
import javax.inject.Inject


class AuthorizationKeyGenerator @Inject constructor() {
	fun generate(timestamp: String, publicKey: String, privateKey: String): String {
		var combinedData = "$timestamp$privateKey$publicKey"
		return combinedData.md5()
	}

}