package dev.katanagari7c1.wolverine.presentation.main

import android.app.Activity
import android.view.View
import android.view.View.OnClickListener
import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.presentation.detail.DetailActivity
import org.jetbrains.anko.startActivity


class ComicListItemClickListener(val activity: Activity): OnClickListener {

	var destinationComic:Comic? = null

	override fun onClick(v: View?) {
		val comic = this.destinationComic ?: return

		activity.startActivity<DetailActivity>(
			DetailActivity.EXTRA_TITLE to comic.title
		)
	}

}