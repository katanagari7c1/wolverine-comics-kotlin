package dev.katanagari7c1.wolverine.presentation.detail

import android.os.Bundle
import android.support.v7.widget.Toolbar
import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindByIdUseCase
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitAuthenticationParametersFactory
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitComicRepository
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitFactory
import dev.katanagari7c1.wolverine.infrastructure.retrofit.util.AuthorizationKeyGenerator
import dev.katanagari7c1.wolverine.presentation.base.DialogActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class DetailActivity : DialogActivity() {

	companion object {
		const val EXTRA_COMIC_ID = "comic_id"
		const val EXTRA_TITLE = "comic_title"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)

		val title = this.intent.getStringExtra(EXTRA_TITLE)
		val comicId = this.intent.getStringExtra(EXTRA_COMIC_ID)

		this.initializeToolbar(find<Toolbar>(R.id.detail_toolbar)).withUpNavigation().title(title)
		this.fetchComicAndRender(comicId)
	}

	private fun fetchComicAndRender(comicId:String) {
		this.showLoading(R.string.loading_comic_data)

		val comicFindByIdUseCase = ComicFindByIdUseCase(
			RetrofitComicRepository(
				retrofitFactory = RetrofitFactory(),
				parametersFactory = RetrofitAuthenticationParametersFactory(
					authorizationKeyGenerator = AuthorizationKeyGenerator()
				)
			)
		)
		doAsync {
			val comic = comicFindByIdUseCase.execute(comicId)
			uiThread {
				if (comic != null) {
					renderComic(comic)

					hideLoading()
				}
			}
		}
	}

	private fun renderComic(comic: Comic) {
		this.detail_description.text = comic.description
	}
}
