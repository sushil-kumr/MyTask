package com.sushildlh.mytasks.Core

import android.app.Application
import com.airbnb.mvrx.Mavericks

class TaskAppliaction: Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}