package dev.katanagari7c1.wolverine.presentation.main

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindAllUseCase
import dev.katanagari7c1.wolverine.infrastructure.MockComicRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		this.initializeComicList()
	}

	private fun initializeComicList() {
		val comicFindAllUseCase = ComicFindAllUseCase(MockComicRepository())
		this.main_comic_recycler_view.layoutManager = LinearLayoutManager(this)
		this.main_comic_recycler_view.adapter = ComicListAdapter(comicFindAllUseCase.execute())
	}
}
