package dev.katanagari7c1.wolverine.presentation.main.list

import android.support.v7.widget.LinearLayoutManager
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener
import dev.katanagari7c1.wolverine.presentation.main.data_loader.LoadMoreItemsCallback

class ComicListScrollListener(
	itemsPerRequest: Int,
	layoutManager: android.support.v7.widget.LinearLayoutManager,
	val loadMoreItemsCallback: dev.katanagari7c1.wolverine.presentation.main.data_loader.LoadMoreItemsCallback
): com.github.pwittchen.infinitescroll.library.InfiniteScrollListener(itemsPerRequest, layoutManager) {

	override fun onScrolledToEnd(firstVisibleItemPosition: Int) {
		this.loadMoreItemsCallback.shouldLoadMoreItems()
	}

}

