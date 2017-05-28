package dev.katanagari7c1.wolverine.presentation.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindFromOffsetUseCase
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitAuthenticationParametersFactory
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitComicRepository
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitFactory
import dev.katanagari7c1.wolverine.infrastructure.retrofit.util.AuthorizationKeyGenerator
import dev.katanagari7c1.wolverine.presentation.base.DialogActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class MainActivity : DialogActivity(), LoadMoreItemsCallback {

	private lateinit var adapter:ComicListAdapter
	private lateinit var dataLoader:ComicListDataLoader
	private var isRequestingComics = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		this.initializeToolbar(find<Toolbar>(R.id.main_toolbar)).title(getString(R.string.app_name))

		this.dataLoader = this.initializeDataLoader()
		this.initializeList(this.dataLoader)
		this.fetchComics()
	}

	private fun initializeDataLoader(): ComicListDataLoader {
		return ComicListDataLoader(
			loadWithOffsetUseCase = ComicFindFromOffsetUseCase(
				RetrofitComicRepository(
					retrofitFactory = RetrofitFactory(),
					parametersFactory = RetrofitAuthenticationParametersFactory(
						authorizationKeyGenerator = AuthorizationKeyGenerator()
					)
				)
			)
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
					isRequestingComics = false
				}

			}
		}
	}

	override fun shouldLoadMoreItems() {
		this.fetchComics()
	}

	private fun initializeList(dataLoader: ComicListDataLoader) {
		this.adapter = ComicListAdapter(this)
		val layoutManager = LinearLayoutManager(this)
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
