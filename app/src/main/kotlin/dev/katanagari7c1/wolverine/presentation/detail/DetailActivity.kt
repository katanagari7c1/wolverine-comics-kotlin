package dev.katanagari7c1.wolverine.presentation.detail

import android.os.Bundle
import android.support.v7.widget.Toolbar

import dev.katanagari7c1.wolverine.R
import dev.katanagari7c1.wolverine.presentation.base.ToolbarActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.find

class DetailActivity : ToolbarActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail)

		this.initializeToolbar(find<Toolbar>(R.id.detail_toolbar)).withUpNavigation().title("Title")

		this.detail_description.text = "Detail screen!"
	}
}
