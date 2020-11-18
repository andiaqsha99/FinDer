package com.tugas.www.finder.expenselimit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tugas.www.finder.R
import kotlinx.android.synthetic.main.activity_expense_limit.*

class ExpenseLimitActivity : AppCompatActivity() {

    private lateinit var expenseLimitPagerAdapter: ExpenseLimitPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_limit)
        setSupportActionBar(toolbar_expense_limit)

        expenseLimitPagerAdapter = ExpenseLimitPagerAdapter(this)
        view_pager_expense.adapter = expenseLimitPagerAdapter

        TabLayoutMediator(tab_expense_limit, view_pager_expense) { tab: TabLayout.Tab, position: Int ->
            tab.text = when(position) {
                0 -> "Daily"
                1 -> "Weekly"
                else -> "Monthly"
            }
        }.attach()
    }
}