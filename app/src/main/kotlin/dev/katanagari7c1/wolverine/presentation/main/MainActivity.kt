package dev.katanagari7c1.wolverine.presentation.main

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.error.FetchError
import dev.katanagari7c1.wolverine.domain.repository.ComicPersistanceRepository
import dev.katanagari7c1.wolverine.domain.repository.ComicRepository
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindAllFromOffsetUseCase
import dev.katanagari7c1.wolverine.domain.use_case.ComicSaveOrUpdateUseCase
import dev.katanagari7c1.wolverine.domain.util.ImageLoader
import dev.katanagari7c1.wolverine.presentation.application.WolverineApplication
import dev.katanagari7c1.wolverine.presentation.base.DialogActivity
import dev.katanagari7c1.wolverine.presentation.main.data_loader.ComicListDataLoader
import dev.katanagari7c1.wolverine.presentation.main.data_loader.LoadMoreItemsCallback
import dev.katanagari7c1.wolverine.presentation.main.list.ComicListAdapter
import dev.katanagari7c1.wolverine.presentation.main.list.ComicListScrollListener
import dev.katanagari7c1.wolverine.presentation.main.search.ComicSearchQueryListener
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import javax.inject.Inject
import javax.inject.Named


class MainActivity : DialogActivity(), LoadMoreItemsCallback {

	@field:[Inject Named("network")]
	lateinit var repository: ComicRepository

	@Inject
	lateinit var persistanceRepository: ComicPersistanceRepository

	@Inject
	lateinit var imageLoader: ImageLoader

	lateinit var dataLoader: ComicListDataLoader

	private lateinit var adapter: ComicListAdapter
	private var isRequestingComics = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		WolverineApplication.getInjectorComponent(this).inject(this)

		this.initializeToolbar(find<Toolbar>(R.id.main_toolbar)).title(getString(R.string.app_name))

		this.dataLoader = this.initializeDataLoader()
		this.initializeList(dataLoader)
		this.fetchComics()
	}


	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.main_menu, menu)

		if(menu != null) {
			this.configureSearchView(menu)
		}

		return true
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		if (item != null) {
			if(item.itemId == R.id.search) {
				this.onSearchRequested()
				return true
			}
			else if (item.itemId == R.id.reload) {
				this.fetchComics()
				return true
			}
		}

		return super.onOptionsItemSelected(item)
	}

	private fun configureSearchView(menu: Menu) {
		val searchItem = menu.findItem(R.id.search)
		val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
		searchView.queryHint = getString(R.string.search_by_title)

		searchView.setOnQueryTextListener(ComicSearchQueryListener(this, searchView))
	}

	private fun initializeDataLoader(): ComicListDataLoader {
		return ComicListDataLoader(
			loadWithOffsetUseCase = ComicFindAllFromOffsetUseCase(repository),
			saveUseCase = ComicSaveOrUpdateUseCase(persistanceRepository)
		)
	}

	private fun fetchComics() {
		if (!this.isRequestingComics) {
			this.isRequestingComics = true
			this.showLoading(R.string.loading_comics)

			doAsync {

				try {
					val comics = dataLoader.load()
					uiThread {
						adapter.appendComics(comics)
						finishRequest()
					}
				}
				catch (error: FetchError) {
					uiThread {
						finishRequest()
						handleFetchError(error)
					}
				}
			}
		}
	}

	private fun finishRequest() {
		this.hideLoading()
		this.isRequestingComics = false
	}

	private fun handleFetchError(error: FetchError) {
		Toast.makeText(this, R.string.error_cannot_fetch, Toast.LENGTH_LONG).show()
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
		this.main_comic_recycler_view.setHasFixedSize(true)
		this.main_comic_recycler_view.layoutManager = layoutManager
		this.main_comic_recycler_view.adapter = this.adapter
		this.main_comic_recycler_view.addOnScrollListener(
			ComicListScrollListener(
				itemsPerRequest = dataLoader.numberOfItemsToLoad,
				layoutManager = layoutManager,
				loadMoreItemsCallback = this
			)
		)
	}
}
