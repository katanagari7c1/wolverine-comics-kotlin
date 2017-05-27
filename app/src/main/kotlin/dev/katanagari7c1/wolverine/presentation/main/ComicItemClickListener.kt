package dev.katanagari7c1.wolverine.presentation.main

import android.app.Activity
import android.view.View
import android.view.View.OnClickListener
import dev.katanagari7c1.wolverine.presentation.detail.DetailActivity
import org.jetbrains.anko.startActivity


class ComicItemClickListener(val activity: Activity): OnClickListener {

	override fun onClick(v: View?) {
		activity.startActivity<DetailActivity>()
	}
}