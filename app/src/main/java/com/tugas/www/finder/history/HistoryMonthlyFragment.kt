package com.tugas.www.finder.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugas.www.finder.R
import kotlinx.android.synthetic.main.fragment_history_monthly.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [HistoryMonthlyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryMonthlyFragment : Fragment() {

    private val viewModel by viewModel<HistoryViewModel>()
    private lateinit var historyAdapter: HistoryDailyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_monthly, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyAdapter = HistoryDailyAdapter("monthly")
        viewModel.apply {
            setListDate()
            setListMonth()
            setListNote()
        }

        rv_history_monthly.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = historyAdapter
        }

        viewModel.getListMonth().observe(viewLifecycleOwner, Observer {
            historyAdapter.setListGroup(it)
        })

        viewModel.getListNote().observe(viewLifecycleOwner, Observer {
            historyAdapter.setListNote(it)
        })
    }
}