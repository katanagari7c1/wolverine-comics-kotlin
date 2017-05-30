package dev.katanagari7c1.wolverine.presentation.application

import android.app.Activity
import android.app.Application
import dev.katanagari7c1.wolverine.infrastructure.dagger2.ActivityModule
import dev.katanagari7c1.wolverine.infrastructure.dagger2.AppComponent
import dev.katanagari7c1.wolverine.infrastructure.dagger2.DaggerAppComponent
import io.realm.Realm


class WolverineApplication: Application() {

	companion object {

		fun getInjectorComponent(activity: Activity):AppComponent {
			return DaggerAppComponent.builder().activityModule(ActivityModule(activity)).build()
		}
	}

	override fun onCreate() {
		super.onCreate()

		Realm.init(this)
	}

}