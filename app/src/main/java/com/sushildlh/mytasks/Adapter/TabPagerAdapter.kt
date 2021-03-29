package com.sushildlh.mytasks.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sushildlh.mytasks.Fragments.DrinkMenuFragment
import com.sushildlh.mytasks.Fragments.MenuFragment
import com.sushildlh.mytasks.Fragments.SushiMenuFragement

class TabPagerAdapter(
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment? = null
        if(position==0) {
            fragment = MenuFragment()
        }else if(position==1){
            fragment = SushiMenuFragement()
        }else{
            fragment = DrinkMenuFragment()
        }
        var arg: Bundle = Bundle()
        arg.putInt("position", position)
        fragment.arguments = arg

        return fragment
    }

    override fun getItemCount(): Int {
        return 3
    }

}