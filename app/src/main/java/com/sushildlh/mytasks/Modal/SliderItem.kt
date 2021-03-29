package com.sushildlh.mytasks.Modal

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class SliderItem : Serializable{
    constructor(imageUrl: String) {
        this.imageUrl = imageUrl
    }
    constructor() {
        imageUrl = ""
    }
    var imageUrl: String
}