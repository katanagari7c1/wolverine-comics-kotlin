package dev.katanagari7c1.wolverine.presentation.base

import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem


abstract class ToolbarActivity: AppCompatActivity() {

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when(item?.itemId) {
			android.R.id.home -> {
				NavUtils.navigateUpFromSameTask(this)
				return true
			}
		}
		return super.onOptionsItemSelected(item)
	}

	fun initializeToolbar(toolbar: Toolbar): ToolbarManager {
		this.setSupportActionBar(toolbar)
		return ToolbarManager(this.supportActionBar)
	}

}