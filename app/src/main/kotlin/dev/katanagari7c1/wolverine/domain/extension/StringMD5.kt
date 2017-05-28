package dev.katanagari7c1.wolverine.domain.extension

import java.security.MessageDigest

// Extension code base on snippet
// https://bitbucket.org/snippets/gelin/zLebo/extension-functions-to-format-bytes-as-hex
//
private val CHARS = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

internal fun Byte.toHexString() : String {
	val i = this.toInt()
	val char2 = CHARS[i and 0x0f]
	val char1 = CHARS[i shr 4 and 0x0f]
	return "$char1$char2"
}

internal fun ByteArray.toHexString() : String {
	val builder = StringBuilder()
	for (b in this) {
		builder.append(b.toHexString())
	}
	return builder.toString()
}
fun String.md5():String {
	val digester = MessageDigest.getInstance("MD5")
	return digester.digest(this.toByteArray(Charsets.UTF_8)).toHexString()
}