package dev.katanagari7c1.wolverine.presentation.main

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.entity.Comic

class ComicListAdapter(val comics:List<Comic>, val activity: Activity): RecyclerView.Adapter<ComicViewHolder>() {

	override fun getItemCount(): Int {
		return this.comics.size
	}

	override fun onBindViewHolder(holder: ComicViewHolder?, position: Int) {
		holder?.bindComic(this.comics[position])
	}

	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ComicViewHolder {
		val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_comic, parent, false)
		return ComicViewHolder(view, ComicItemClickListener(activity))
	}

}
