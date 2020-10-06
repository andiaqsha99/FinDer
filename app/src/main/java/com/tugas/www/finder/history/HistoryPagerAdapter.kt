package com.tugas.www.finder.history

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tugas.www.finder.R

class HistoryPagerAdapter(fm: Fragment): FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when(position) {
            0 -> fragment = HistoryDailyFragment()
            1 -> fragment = HistoryWeeklyFragment()
            2 -> fragment = HistoryMonthlyFragment()
        }

        return fragment as Fragment
    }
}