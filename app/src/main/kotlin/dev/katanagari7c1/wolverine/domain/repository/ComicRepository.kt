package dev.katanagari7c1.wolverine.domain.repository

import dev.katanagari7c1.wolverine.domain.entity.Comic


interface ComicRepository {
	fun findAll():List<Comic>
	fun findById(id:String):Comic?
	fun findFromOffsetAndLimit(offset:Int, limit:Int):List<Comic>
}