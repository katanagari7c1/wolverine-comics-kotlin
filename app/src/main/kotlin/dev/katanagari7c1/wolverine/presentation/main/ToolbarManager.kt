package dev.katanagari7c1.wolverine.presentation.main

import android.support.v7.app.ActionBar


class ToolbarManager(val actionBar: ActionBar?) {

	fun withUpNavigation():ToolbarManager {
		this.actionBar?.setDisplayHomeAsUpEnabled(true)
		return this
	}

	fun title(title:String):ToolbarManager {
		this.actionBar?.title = title
		return this
	}
}