package dev.katanagari7c1.wolverine.infrastructure.retrofit.util

import dev.katanagari7c1.wolverine.domain.extension.md5
import java.util.*


class AuthorizationKeyGenerator {
	fun generate(timestamp: String, publicKey: String, privateKey: String): String {
		var combinedData = "$timestamp$privateKey$publicKey"
		return combinedData.md5()
	}

}