package dev.katanagari7c1.wolverine.infrastructure.retrofit.response


data class ComicListResponseData(
	val offset:Int,
	val limit:Int,
	val total:Int,
	val count:Int,
	val results:List<ComicListResponseDataItem>
) {}