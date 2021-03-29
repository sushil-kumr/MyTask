package com.sushildlh.mytasks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.andremion.counterfab.CounterFab
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sushildlh.mytasks.Adapter.TabPagerAdapter
import com.sushildlh.mytasks.Fragments.DiscountFragment
import com.sushildlh.mytasks.Modal.MenuData
import com.sushildlh.mytasks.Modal.TaskResponse
import com.sushildlh.mytasks.Network.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeActivity : AppCompatActivity() {

    var fab: CounterFab? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var response: TaskResponse? = null
    private var tabPager: ViewPager2? = null
    private var tabs: TabLayout? = null
    private var fragment:DiscountFragment?=null
    private var menuAdapter : TabPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        myCompositeDisposable = CompositeDisposable()

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.hide()
        tabPager = findViewById(R.id.viewpager)
        tabs = findViewById(R.id.tabs)

        fragment = getSupportFragmentManager().findFragmentById(R.id.frag1) as DiscountFragment?

        fab = findViewById(R.id.fab)

        fab?.setOnClickListener { view ->
            Toast.makeText(this,"I need to implement Fragment for another page",Toast.LENGTH_LONG).show()
        }

        if(savedInstanceState==null)
            loadData()
        else{
            val fm: FragmentManager = supportFragmentManager
            menuAdapter =  TabPagerAdapter(fm, lifecycle)
            tabPager?.adapter = menuAdapter
            tabs?.setSelectedTabIndicator(null);
            TabLayoutMediator(tabs!!, tabPager!!) { tab, position ->
                tab.text = "${getTabNames(position)}"
            }.attach()
        }
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

    public fun updateSet(count:Int) {
        fab?.count = count; // Increase the cart item value by 1
    }

    private fun handleResponse(response: TaskResponse) {
        this.response = response
        fragment?.setItem(response.discountImages)

        val fm: FragmentManager = supportFragmentManager
        menuAdapter =  TabPagerAdapter(fm, lifecycle)
        tabPager?.adapter = menuAdapter
        tabs?.setSelectedTabIndicator(null);
        TabLayoutMediator(tabs!!, tabPager!!) { tab, position ->
            tab.text = "${getTabNames(position)}"
        }.attach()
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(this,"Check You Internet Connection and Restart App",Toast.LENGTH_LONG).show()
    }

    public fun getData(): TaskResponse {
        if(this.response !=null)
            return this.response!!
        else return TaskResponse()
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

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
    }
}