package dev.katanagari7c1.wolverine.infrastructure.realm

import io.realm.RealmObject


open class RealmImageListItem(var path:String):RealmObject() {
	constructor():this("")
}