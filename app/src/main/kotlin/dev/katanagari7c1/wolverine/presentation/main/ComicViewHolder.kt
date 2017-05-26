package dev.katanagari7c1.wolverine.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.View
import dev.katanagari7c1.wolverine.domain.entity.Comic
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicViewHolder(val view: View): RecyclerView.ViewHolder(view) {

	fun bindComic(comic:Comic) {
		view.item_comic_title.text = comic.title
	}
}