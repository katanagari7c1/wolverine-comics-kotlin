package dev.katanagari7c1.wolverine.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.View
import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.util.ImageLoader
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicViewHolder(val view: View, val imageLoader:ImageLoader, val listener:ComicListItemClickListener): RecyclerView.ViewHolder(view) {

	fun bindComic(comic:Comic) {
		view.item_comic_title.text = comic.title
		this.imageLoader.loadImageInto(comic.thumbnail, view.item_comic_image)

		listener.destinationComic = comic
		view.setOnClickListener(listener)
	}
}