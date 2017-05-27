package dev.katanagari7c1.wolverine.presentation.detail

import android.os.Bundle
import android.support.v7.widget.Toolbar

import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindByTitleUseCase
import dev.katanagari7c1.wolverine.infrastructure.MockComicRepository
import dev.katanagari7c1.wolverine.presentation.base.ToolbarActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.find

class DetailActivity : ToolbarActivity() {

	companion object {
		const val EXTRA_TITLE = "comic_title"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)

		val title = this.intent.getStringExtra(EXTRA_TITLE)
		val comic = ComicFindByTitleUseCase(MockComicRepository()).execute(title)

		this.initializeToolbar(find<Toolbar>(R.id.detail_toolbar)).withUpNavigation().title(title)

		if (comic != null) {
			this.renderComic(comic)
		}
	}

	private fun renderComic(comic: Comic) {
		this.detail_description.text = comic.description
	}
}
