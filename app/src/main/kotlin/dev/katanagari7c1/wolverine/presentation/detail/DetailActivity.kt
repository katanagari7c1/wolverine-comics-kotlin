package dev.katanagari7c1.wolverine.presentation.detail

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.entity.Comic
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindByIdUseCase
import dev.katanagari7c1.wolverine.domain.util.ImageLoader
import dev.katanagari7c1.wolverine.presentation.application.WolverineApplication
import dev.katanagari7c1.wolverine.presentation.base.DialogActivity
import dev.katanagari7c1.wolverine.presentation.helper.StringToHtmlConverter
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class DetailActivity : DialogActivity() {

	companion object {
		const val EXTRA_COMIC_ID = "comic_id"
	}

	@field:[Inject Named ("persistence")]
	lateinit var repository: ComicRepository

	@Inject
	lateinit var stringToHtmlConverter: StringToHtmlConverter

	@Inject
	lateinit var imageLoader: ImageLoader

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)
		WolverineApplication.getInjectorComponent(this).inject(this)

		val comicId = this.intent.getStringExtra(EXTRA_COMIC_ID)

		this.initializeToolbar(find<Toolbar>(R.id.detail_toolbar))
			.withUpNavigation()
			.title(getString(R.string.detail_title))
		this.fetchComicAndRender(comicId)
	}

	private fun fetchComicAndRender(comicId:String) {
		this.showLoading(R.string.loading_comic_data)

		val comicFindByIdUseCase = ComicFindByIdUseCase(repository)
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
		this.detail_title.text = comic.title
		this.detail_description.text = if (comic.description.isEmpty())
				getString(R.string.no_description)
			else
				stringToHtmlConverter.convert(comic.description)

		this.showRandomImage(comic, detail_image)
		this.detail_series_field.text = comic.series
		this.detail_pages.text = String.format(getString(R.string.page_number_template), comic.numPages)
	}

	private fun showRandomImage(comic: Comic, detail_image: ImageView) {
		if (comic.images.isEmpty()) {
			this.imageLoader.loadImageInto(comic.thumbnail, detail_image)
		}
		else {
			val randomIndex = Random().nextInt(comic.images.size)
			this.imageLoader.loadImageInto(comic.images[randomIndex], detail_image)
		}
	}
}
