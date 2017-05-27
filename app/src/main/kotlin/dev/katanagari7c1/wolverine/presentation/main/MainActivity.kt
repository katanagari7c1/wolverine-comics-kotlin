package dev.katanagari7c1.wolverine.presentation.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar

import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindAllUseCase
import dev.katanagari7c1.wolverine.infrastructure.MockComicRepository
import dev.katanagari7c1.wolverine.presentation.base.ToolbarActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : ToolbarActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		this.initializeToolbar(find<Toolbar>(R.id.main_toolbar)).title(getString(R.string.app_name))

		this.initializeComicList()
	}

	private fun initializeComicList() {
		val comicFindAllUseCase = ComicFindAllUseCase(MockComicRepository())
		this.main_comic_recycler_view.layoutManager = LinearLayoutManager(this)
		this.main_comic_recycler_view.adapter = ComicListAdapter(comicFindAllUseCase.execute(), this)
	}
}
