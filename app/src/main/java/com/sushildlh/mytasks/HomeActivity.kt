package com.sushildlh.mytasks

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.andremion.counterfab.CounterFab
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sushildlh.mytasks.Adapter.MyPagerAdapter
import com.sushildlh.mytasks.Adapter.TabPagerAdapter
import com.sushildlh.mytasks.Core.TaskBaseActivity
import com.sushildlh.mytasks.Modal.MenuData
import com.sushildlh.mytasks.Modal.TaskResponse
import com.sushildlh.mytasks.Network.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeActivity : TaskBaseActivity() {

    var fab: CounterFab? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var response: TaskResponse? = null
    private var sliderPager: ViewPager2? = null
    private var imageAdapter = MyPagerAdapter()
    private var tabPager: ViewPager2? = null
    private var tabs: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        myCompositeDisposable = CompositeDisposable()

        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.hide()

        sliderPager = findViewById(R.id.slider_pager)
        var sliderTab: TabLayout = findViewById(R.id.slider_tab)
        tabPager = findViewById(R.id.viewpager)
        tabs = findViewById(R.id.tabs)

        sliderPager?.adapter = imageAdapter

        TabLayoutMediator(sliderTab, sliderPager!!) { tab, position ->
        }.attach()

        fab = findViewById(R.id.fab)

        fab?.setOnClickListener { view ->
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        loadData()
    }

    private fun loadData() {
        myCompositeDisposable?.add(
            ServiceBuilder.buildService().getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response -> handleResponse(response) }, { t -> handleError(t) })
        )
    }

    public fun updateCartItem() {
        fab?.increase(); // Increase the cart item value by 1
    }

    private fun handleResponse(response: TaskResponse) {
        this.response = response
        imageAdapter.setItem(response.discountImages)

        val fm: FragmentManager = supportFragmentManager
        tabPager?.adapter = TabPagerAdapter(fm, lifecycle)
        tabs?.setSelectedTabIndicator(null);
        TabLayoutMediator(tabs!!, tabPager!!) { tab, position ->
            tab.text = "${getTabNames(position)}"
        }.attach()
    }

    private fun handleError(error: Throwable) {
        Log.d("sam", error.toString())
    }

    public fun getData(): TaskResponse {
        return this.response!!
    }

    public fun getMenuData(position: Int): List<MenuData>? {
        when (position) {
            0 -> return this.response?.pizzaData
            1 ->
                return this.response?.sushiData
            else ->
                return this.response?.drinkData
        }
    }

    fun getTabNames(position: Int): String {
        when (position) {
            0 -> return "Pizza"
            1 -> return "Sushi"
            else -> return "Drink"
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        myCompositeDisposable?.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
    }
}