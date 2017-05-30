package dev.katanagari7c1.wolverine.presentation.main.list

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.util.ImageLoader
import dev.katanagari7c1.wolverine.presentation.main.list.holder.ComicViewHolder
import dev.katanagari7c1.wolverine.presentation.main.listener.ComicListItemClickListener

class ComicListAdapter(val activity: Activity, val imageLoader: ImageLoader): RecyclerView.Adapter<ComicViewHolder>() {

	var comics:MutableList<Comic> = mutableListOf()

	fun appendComics(newComics: List<Comic>) {
		this.comics.addAll(newComics)
		this.notifyDataSetChanged()
	}

	fun replaceComics(newComics: List<Comic>) {
		this.comics.clear()
		this.comics.addAll(newComics)
		this.notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return this.comics.size
	}

	override fun onBindViewHolder(holder: ComicViewHolder?, position: Int) {
		holder?.bindComic(this.comics[position])
	}

	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ComicViewHolder {
		val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_comic, parent, false)
		return ComicViewHolder(view, imageLoader, ComicListItemClickListener(activity))
	}

}
