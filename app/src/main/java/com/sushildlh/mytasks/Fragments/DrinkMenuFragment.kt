package com.sushildlh.mytasks.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.*
import com.sushildlh.mytasks.Adapter.MenuAdapter
import com.sushildlh.mytasks.HomeActivity
import com.sushildlh.mytasks.Modal.MenuData
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

data class DrinkMenuState(@PersistState val menList: List<MenuData> = emptyList()) : MavericksState

class DrinkMenuViewModel(state: DrinkMenuState) : MavericksViewModel<DrinkMenuState>(state) {

    fun setList(menList:List<MenuData>) = setState { copy(menList = menList) }
}

class DrinkMenuFragment : Fragment(), MavericksView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: MenuViewModel by activityViewModel()

//    private var position: Int = 0
    private var mList: RecyclerView?=null

    override fun invalidate() = withState(viewModel) { state ->
        setAdapter(state.menList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//        position = arguments?.getInt("position")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_menu, container, false)

        val activity = this.context as HomeActivity
        var menuList: List<MenuData> = emptyList<MenuData>()
        if (activity.getMenuData(2) != null) {
            menuList = activity.getMenuData(2)!!
            if(menuList!=null) {
                viewModel.setList(menuList)
            }
        }



        mList = view?.findViewById(R.id.menu_list)


        return view
    }

    private fun setAdapter(menuList:List<MenuData>){
        mList?.layoutManager = LinearLayoutManager(this.activity)
        mList?.addItemDecoration(
            DividerItemDecoration(
                this.context,
                LinearLayoutManager.VERTICAL
            )
        )
        mList?.adapter = this.context?.let { MenuAdapter(menuList, it) }
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