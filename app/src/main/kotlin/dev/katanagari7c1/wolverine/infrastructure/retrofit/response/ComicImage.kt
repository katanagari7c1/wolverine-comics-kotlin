package dev.katanagari7c1.wolverine.infrastructure.retrofit.response


data class ComicImage(
	val path:String,
	val extension:String
) {
	fun fullPath():String {
		return "$path.$extension"
	}
}