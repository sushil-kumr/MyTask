package com.sushildlh.mytasks

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sushildlh.mytasks.Adapter.CartMenuAdapter
import com.sushildlh.mytasks.Adapter.MenuAdapter
import com.sushildlh.mytasks.Modal.MenuData

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Checkout"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val menuList = listOf(
            MenuData(),
            MenuData(),
            MenuData(),
        )

        var mList: RecyclerView = findViewById(R.id.menu_list)
        mList.layoutManager = LinearLayoutManager(this)
        mList.adapter = CartMenuAdapter(menuList)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}