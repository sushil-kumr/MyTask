package com.sushildlh.mytasks.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.*
import com.sushildlh.mytasks.Adapter.MenuAdapter
import com.sushildlh.mytasks.HomeActivity
import com.sushildlh.mytasks.Modal.MenuData
import com.sushildlh.mytasks.Modal.SliderItem
import com.sushildlh.mytasks.Modal.TaskResponse
import com.sushildlh.mytasks.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

data class MenuState(@PersistState val menList: TaskResponse? = TaskResponse()) : MavericksState

class MenuViewModel(state: MenuState) : MavericksViewModel<MenuState>(state) {

    fun setList(menList: TaskResponse) = setState { copy(menList = menList) }
}

class MenuFragment : Fragment(), MavericksView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: MenuViewModel by activityViewModel()
    private var position: Int = 1

    private var mList: RecyclerView? = null

    override fun invalidate() = withState(viewModel) { state ->
        if (position == 0)
            setAdapter(state.menList!!.pizzaData)
        else if(position==1)
            setAdapter(state.menList!!.sushiData)
        else
            setAdapter(state.menList!!.drinkData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            position = it.getInt("position")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_menu, container, false)

        val activity = this.context as HomeActivity
        var menuList: List<MenuData> = emptyList<MenuData>()
        if (activity.getMenuData(0) != null) {
            menuList = activity.getMenuData(0)!!
            if (menuList != null) {
                viewModel.setList(activity.getData())
            }
        }

        mList = view?.findViewById(R.id.menu_list)


        return view
    }

    private fun setAdapter(menuList: List<MenuData>) {
        mList?.layoutManager = LinearLayoutManager(this.activity)
        mList?.addItemDecoration(
            DividerItemDecoration(
                this.context,
                LinearLayoutManager.VERTICAL
            )
        )
        mList?.adapter = this.context?.let { MenuAdapter(menuList, it) }
    }

    fun show() {
//        Toast.makeText(this.context,"Check You Internet Connection and Restart App", Toast.LENGTH_LONG).show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}