package com.tugas.www.finder.chart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.tugas.www.finder.R
import com.tugas.www.finder.home.HomeMainAdapter
import com.tugas.www.finder.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [ChartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChartFragment : Fragment() {

    private val category = arrayOf("Income", "Expense")
    private var number: MutableList<Int> = ArrayList()
    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var homeMainAdapter: HomeMainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            homeMainAdapter = HomeMainAdapter()
            viewModel.apply {
                setListNote()
            }

            viewModel.getListNote().observe(viewLifecycleOwner, Observer{
                homeMainAdapter.setNote(it)
                var income = 0
                var expense = 0

                for (i in it.indices){
                    if (it[i].type == "income"){
                        income += it[i].amount
                    }else{
                        expense += it[i].amount
                    }
                }

                number.add(income)
                number.add(expense)

                setupPieChart()

            })
        }catch(e: Exception){
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT)
        }
    }

    private fun setupPieChart(){
        val pie: Pie = AnyChart.pie()
        val dataEntries: MutableList<DataEntry> = ArrayList()

        for(i in category.indices){
            dataEntries.add(ValueDataEntry(category[i], number[i]))
        }

        pie.data(dataEntries)
        any_chart_view.setChart(pie)
    }
}