package com.tugas.www.finder.expenselimit

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ExpenseLimitPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when(position) {
            0 -> fragment = DailyExpenseLimitFragment()
            1 -> fragment = WeeklyExpenseLimitFragment()
            2 -> fragment = MonthlyExpenseLimitFragment()
        }

        return fragment as Fragment
    }
}