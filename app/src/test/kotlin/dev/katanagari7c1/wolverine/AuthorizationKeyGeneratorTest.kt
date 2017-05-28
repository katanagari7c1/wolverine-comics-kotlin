package dev.katanagari7c1.wolverine

import dev.katanagari7c1.wolverine.infrastructure.retrofit.util.AuthorizationKeyGenerator
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class AuthorizationKeyGeneratorTest {

	private lateinit var keyGenerator: AuthorizationKeyGenerator

	@Before
	fun setup() {
		this.keyGenerator = AuthorizationKeyGenerator()
	}

	@Test
	fun test_it_generates_a_valid_key_combining_the_parameters() {
		val date = "872838840" // Fri, 29 Aug 1997 02:14:00 -0500
		val publicKey = "5c195583694ba61004b93a2baf0221e9"
		val privateKey = "fbe1e5293803fefce1189a1a0d5ac848"
		val expectedAuthorizationKey = "f09ccc0b552856a7dd91d388eba229ca" // md5(ts+privateKey+publicKey))

		assertEquals(expectedAuthorizationKey, this.keyGenerator.generate(date, publicKey, privateKey) ?: "")
	}
}