package dev.katanagari7c1.wolverine.infrastructure.dagger2

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: Activity) {

	@Provides
	fun providesContext(): Context {
		return activity
	}
}