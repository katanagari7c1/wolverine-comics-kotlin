package dev.katanagari7c1.wolverine.presentation.main.list

import android.support.v7.util.DiffUtil
import dev.katanagari7c1.wolverine.domain.entity.Comic

class ComicListDiffCallback(
	val oldList:List<dev.katanagari7c1.wolverine.domain.entity.Comic>,
	val newList:List<dev.katanagari7c1.wolverine.domain.entity.Comic>
): android.support.v7.util.DiffUtil.Callback() {

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return this.oldList[oldItemPosition].comicId == this.newList[newItemPosition].comicId
	}

	override fun getOldListSize(): Int {
		return this.oldList.size
	}

	override fun getNewListSize(): Int {
		return this.newList.size
	}

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return this.oldList[oldItemPosition].title == this.newList[newItemPosition].title &&
			this.oldList[oldItemPosition].thumbnail == this.newList[newItemPosition].thumbnail
	}

}

