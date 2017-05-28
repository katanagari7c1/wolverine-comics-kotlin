package dev.katanagari7c1.wolverine.presentation.main

import android.support.v7.widget.LinearLayoutManager
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener

class ComicListScrollListener(
	itemsPerRequest: Int,
	layoutManager: LinearLayoutManager,
	val loadMoreItemsCallback: LoadMoreItemsCallback
): InfiniteScrollListener(itemsPerRequest, layoutManager) {

	override fun onScrolledToEnd(firstVisibleItemPosition: Int) {
		this.loadMoreItemsCallback.shouldLoadMoreItems()
	}

}

