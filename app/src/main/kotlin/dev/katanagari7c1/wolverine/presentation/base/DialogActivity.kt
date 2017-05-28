package dev.katanagari7c1.wolverine.presentation.base

import android.app.ProgressDialog
import android.content.Context
import android.view.inputmethod.InputMethodManager
import org.jetbrains.anko.indeterminateProgressDialog


abstract class DialogActivity: ToolbarActivity() {

	var activeDialog: ProgressDialog? = null

	fun showLoading(messageId:Int) {
		this.activeDialog = indeterminateProgressDialog(messageId)
		this.activeDialog?.show()
	}

	fun hideLoading() {
		this.activeDialog?.dismiss()
		this.activeDialog = null
	}

}