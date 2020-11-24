package com.tugas.www.finder.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugas.www.finder.R
import com.tugas.www.finder.formatToRupiah
import com.tugas.www.finder.getWeekOfDate
import kotlinx.android.synthetic.main.fragment_history_weekly.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryWeeklyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryWeeklyFragment : Fragment() {

    private val viewModel by viewModel<HistoryViewModel>()
    private lateinit var historyAdapter: HistoryMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_weekly, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyAdapter = HistoryMainAdapter("weekly")
        viewModel.apply {
            setListDate()
            setListMonth()
            setListNote()
            setSumExpense()
            setSumIncome()
        }

        rv_history_weekly.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = historyAdapter
        }

        val listWeek = arrayListOf<String>()
        viewModel.getListDate().observe(viewLifecycleOwner, Observer { list ->
            if (list.isNotEmpty()) {
                for (date in list) {
                    val weekOfYear = getWeekOfDate(date)
                    if (!listWeek.contains(weekOfYear)) {
                        listWeek.add(weekOfYear)
                    }
                }
            }
            historyAdapter.setListGroup(listWeek)
        })

        viewModel.getListNote().observe(viewLifecycleOwner, Observer {
            historyAdapter.setListNote(it)
        })

        tv_total_expense_weekly.text = formatToRupiah(0)
        tv_total_income_weekly.text = formatToRupiah(0)

        viewModel.getSumExpense().observe(viewLifecycleOwner, {
            if (it != null) {
                tv_total_expense_weekly.text = formatToRupiah(it.toLong())
            }
        })

        viewModel.getSumIncome().observe(viewLifecycleOwner, {
            if (it != null) {
                tv_total_income_weekly.text = formatToRupiah(it.toLong())
            }
        })
    }
}