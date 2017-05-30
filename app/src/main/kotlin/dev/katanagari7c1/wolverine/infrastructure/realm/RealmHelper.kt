package dev.katanagari7c1.wolverine.infrastructure.realm

import io.realm.Realm
import javax.inject.Inject


class RealmHelper @Inject constructor() {
	fun getRealmInstance():Realm {
		return Realm.getDefaultInstance()
	}
}