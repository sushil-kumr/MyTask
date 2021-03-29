package com.sushildlh.mytasks.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.mvrx.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sushildlh.mytasks.Adapter.MyPagerAdapter
import com.sushildlh.mytasks.Modal.MenuData
import com.sushildlh.mytasks.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiscountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


data class DiscountState(@PersistState val data: List<String> = emptyList()) : MavericksState

class DiscountViewModel(state: DiscountState) : MavericksViewModel<DiscountState>(state) {

    fun setList(data: List<String>) = setState { copy(data = data) }
}

class DiscountFragment : Fragment(), MavericksView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var sliderPager: ViewPager2? = null
    private var imageAdapter = MyPagerAdapter()

    private val viewModel: DiscountViewModel by activityViewModel()


    override fun invalidate() = withState(viewModel) { state ->
        imageAdapter.setItem(state.data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view: View = inflater.inflate(R.layout.fragment_discount, container, false)

        sliderPager = view.findViewById(R.id.slider_pager)

        var sliderTab: TabLayout = view.findViewById(R.id.slider_tab)
        sliderPager?.adapter = imageAdapter

        TabLayoutMediator(sliderTab, sliderPager!!) { tab, position ->
        }.attach()

        return view;
    }

    fun setItem(discountImages: List<String>) {
        if (discountImages != null){
            viewModel.setList(discountImages)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DiscountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiscountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}