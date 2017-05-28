package dev.katanagari7c1.wolverine.presentation.main.search

import android.support.v7.widget.SearchView
import dev.katanagari7c1.wolverine.presentation.base.DialogActivity
import dev.katanagari7c1.wolverine.presentation.search_result.SearchResultActivity
import org.jetbrains.anko.startActivity

class ComicSearchQueryListener(val activity: DialogActivity, val searchView:SearchView): SearchView.OnQueryTextListener {

	override fun onQueryTextSubmit(query: String?): Boolean {

		this.searchView.clearFocus()

		if (query != null) {
			this.activity.startActivity<SearchResultActivity>(
				SearchResultActivity.EXTRA_SEARCH_QUERY to query
			)
		}

		return true
	}

	override fun onQueryTextChange(newText: String?): Boolean {
		return false
	}
}