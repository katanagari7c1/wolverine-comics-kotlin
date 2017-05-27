package dev.katanagari7c1.wolverine.presentation.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dev.katanagari7c1.wolverine.presentation.main.MainActivity
import org.jetbrains.anko.startActivity


class LauncherActivity():AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		startActivity<MainActivity>()
		finish()
	}
}