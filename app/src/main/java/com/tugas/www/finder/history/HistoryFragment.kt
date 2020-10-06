package com.tugas.www.finder.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.tugas.www.finder.R
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {

    private lateinit var historyPagerAdapter: HistoryPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyPagerAdapter = HistoryPagerAdapter(this)
        view_pager.adapter = historyPagerAdapter

        TabLayoutMediator(tab_history, view_pager) { tab, position ->
            tab.text = when(position) {
                0 -> resources.getString(R.string.daily_history)
                1 -> resources.getString(R.string.weekly_history)
                else -> resources.getString(R.string.monthly_history)
            }
        }.attach()
    }
}