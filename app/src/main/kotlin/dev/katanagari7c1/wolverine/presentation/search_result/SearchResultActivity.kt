package dev.katanagari7c1.wolverine.presentation.search_result

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View.GONE
import android.view.View.VISIBLE
import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.repository.ComicPersistanceRepository
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindContainingTextInTitleAndOffsetUseCase
import dev.katanagari7c1.wolverine.domain.use_case.ComicSaveOrUpdateUseCase
import dev.katanagari7c1.wolverine.domain.util.ImageLoader
import dev.katanagari7c1.wolverine.presentation.application.WolverineApplication
import dev.katanagari7c1.wolverine.presentation.base.DialogActivity
import dev.katanagari7c1.wolverine.presentation.main.data_loader.ComicListDataLoader
import dev.katanagari7c1.wolverine.presentation.main.data_loader.LoadMoreItemsCallback
import dev.katanagari7c1.wolverine.presentation.main.list.ComicListAdapter
import dev.katanagari7c1.wolverine.presentation.main.list.ComicListScrollListener
import kotlinx.android.synthetic.main.activity_search_result.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import javax.inject.Inject
import javax.inject.Named


class SearchResultActivity : DialogActivity(), LoadMoreItemsCallback {

	companion object {
		const val EXTRA_SEARCH_QUERY = "search_query"
	}

	@field:[Inject Named("network")]
	lateinit var repository:ComicRepository

	@Inject
	lateinit var persistanceRepository: ComicPersistanceRepository

	@Inject
	lateinit var imageLoader:ImageLoader

	private lateinit var adapter: ComicListAdapter
	private lateinit var dataLoader: ComicListDataLoader
	private var isRequestingComics = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_search_result)

		WolverineApplication.getInjectorComponent(this).inject(this)

		val queryString = getQueryFromIntent(this.intent)

		this.initializeToolbar(find<Toolbar>(R.id.search_toolbar))
			.withUpNavigation()
			.title(formatTitle(queryString))

		this.dataLoader = this.initializeDataLoader(queryString)
		this.initializeList(this.dataLoader)
		this.fetchComics()
	}

	private fun formatTitle(queryString: String): String {
		return String.format(getString(R.string.search_screen_title), queryString)
	}

	private fun  getQueryFromIntent(intent: Intent?): String {
		if (intent != null) {
			return intent.getStringExtra(EXTRA_SEARCH_QUERY)
		}

		return ""
	}

	private fun initializeDataLoader(queryString: String): ComicListDataLoader {
		return ComicListDataLoader(
			loadWithOffsetUseCase = ComicFindContainingTextInTitleAndOffsetUseCase(queryString, repository),
			saveUseCase = ComicSaveOrUpdateUseCase(persistanceRepository)
		)
	}

	private fun fetchComics() {
		if (!this.isRequestingComics) {
			this.isRequestingComics = true
			this.showLoading(R.string.loading_comics)

			doAsync {
				val comics = dataLoader.load()

				uiThread {
					adapter.appendComics(comics)

					hideLoading()
					showNoResultScreen(adapter)
					isRequestingComics = false
				}

			}
		}
	}

	private fun showNoResultScreen(adapter: ComicListAdapter) {
		this.search_no_result_view.visibility = if (adapter.comics.isEmpty()) VISIBLE else GONE
	}

	override fun shouldLoadMoreItems() {
		this.fetchComics()
	}

	private fun initializeList(dataLoader: ComicListDataLoader) {
		this.adapter = ComicListAdapter(
			activity = this,
			imageLoader = this.imageLoader
		)
		val itemsPerRow = resources.getInteger(R.integer.items_per_row)
		val layoutManager = GridLayoutManager(this, itemsPerRow)
		this.search_comic_recycler_view.setHasFixedSize(true)
		this.search_comic_recycler_view.layoutManager = layoutManager
		this.search_comic_recycler_view.adapter = this.adapter
		this.search_comic_recycler_view.addOnScrollListener(
			ComicListScrollListener(
				itemsPerRequest = dataLoader.numberOfItemsToLoad,
				layoutManager = layoutManager,
				loadMoreItemsCallback = this
			)
		)
	}
}