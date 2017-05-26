package dev.katanagari7c1.wolverine.domain.entity

data class Comic(
	val title:String,
	val description:String,
	val thumbnail:String,
	val images:List<String>
)
