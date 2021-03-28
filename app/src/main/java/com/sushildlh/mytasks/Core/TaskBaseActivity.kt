package com.sushildlh.mytasks.Core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.mvrx.MvRxViewModelStore
import com.airbnb.mvrx.MvRxViewModelStoreOwner

open class TaskBaseActivity : AppCompatActivity(), MvRxViewModelStoreOwner {

    override val mvrxViewModelStore by lazy { MvRxViewModelStore(viewModelStore) }

    override fun onCreate(savedInstanceState: Bundle?) {
        mvrxViewModelStore.restoreViewModels(this, savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvrxViewModelStore.saveViewModels(outState)
    }
}