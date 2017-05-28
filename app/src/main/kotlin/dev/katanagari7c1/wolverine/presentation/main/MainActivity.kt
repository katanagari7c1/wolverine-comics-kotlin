package dev.katanagari7c1.wolverine.presentation.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar

import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.domain.use_case.ComicFindAllUseCase
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitAuthenticationParametersFactory
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitComicRepository
import dev.katanagari7c1.wolverine.infrastructure.retrofit.RetrofitFactory
import dev.katanagari7c1.wolverine.infrastructure.retrofit.util.AuthorizationKeyGenerator
import dev.katanagari7c1.wolverine.presentation.base.DialogActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class MainActivity : DialogActivity() {

	private var adapter:ComicListAdapter? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		this.initializeToolbar(find<Toolbar>(R.id.main_toolbar)).title(getString(R.string.app_name))

		this.initializeList()
		this.fetchComics()
	}

	private fun fetchComics() {
		this.showLoading(R.string.loading_comics)

		val comicFindAllUseCase = ComicFindAllUseCase(
			RetrofitComicRepository(
				retrofitFactory = RetrofitFactory(),
				parametersFactory = RetrofitAuthenticationParametersFactory(
					authorizationKeyGenerator = AuthorizationKeyGenerator()
				)
			)
		)
		doAsync {
			val comics = comicFindAllUseCase.execute()
			uiThread {
				adapter?.comics = comics
				adapter?.notifyDataSetChanged()

				hideLoading()
			}
		}
	}

	private fun initializeList() {
		this.adapter = ComicListAdapter(listOf(), this)
		this.main_comic_recycler_view.layoutManager = LinearLayoutManager(this)
		this.main_comic_recycler_view.adapter = this.adapter

	}
}
