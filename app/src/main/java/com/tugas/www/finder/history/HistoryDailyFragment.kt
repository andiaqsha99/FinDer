package com.tugas.www.finder.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugas.www.finder.R
import com.tugas.www.finder.formatToRupiah
import kotlinx.android.synthetic.main.fragment_history_daily.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryDailyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryDailyFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_history_daily, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyAdapter = HistoryMainAdapter("daily")
        viewModel.apply {
            setListDate()
            setListMonth()
            setListNote()
            setSumExpense()
            setSumIncome()
        }

        rv_history_daily.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }

        viewModel.getListDate().observe(viewLifecycleOwner, Observer {
            historyAdapter.setListGroup(it)
        })

        viewModel.getListNote().observe(viewLifecycleOwner, Observer {
            historyAdapter.setListNote(it)
        })

        tv_total_expense_daily.text = formatToRupiah(0)
        tv_total_income_daily.text = formatToRupiah(0)

        viewModel.getSumExpense().observe(viewLifecycleOwner, {
            if (it !=null) {
                tv_total_expense_daily.text = formatToRupiah(it.toLong())
            }
        })

        viewModel.getSumIncome().observe(viewLifecycleOwner, {
            if (it !=null) {
                tv_total_income_daily.text = formatToRupiah(it.toLong())
            }
        })
    }
}