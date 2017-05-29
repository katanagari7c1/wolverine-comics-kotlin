package dev.katanagari7c1.wolverine.presentation.helper

import android.text.Html
import android.os.Build
import android.text.Spanned
import javax.inject.Inject


class StringToHtmlConverter @Inject constructor() {

	fun convert(text: String): Spanned {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
		} else {
			return Html.fromHtml(text)
		}
	}

}