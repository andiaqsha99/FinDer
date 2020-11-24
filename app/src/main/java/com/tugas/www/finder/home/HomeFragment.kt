package com.tugas.www.finder.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialFade
import com.tugas.www.finder.R
import com.tugas.www.finder.expenselimit.ExpenseLimitActivity
import com.tugas.www.finder.fab.FabMenu
import com.tugas.www.finder.fab.FabMenuAdapter
import com.tugas.www.finder.formatToRupiah
import com.tugas.www.finder.inputmonetary.InputExpenseActivity
import com.tugas.www.finder.inputmonetary.InputIncomeActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var homeMainAdapter: HomeMainAdapter
    private var sumIncome = 0
    private var sumExpense = 0
    private var balance = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMainAdapter = HomeMainAdapter()
        viewModel.apply {
            setListNote()
            setListDate()
            setSumExpense()
            setSumIncome()
        }
        rv_main_monetary.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeMainAdapter
        }

        viewModel.getSumExpense().observe(viewLifecycleOwner, {
            if (it != null) {
                sumExpense = it
            }
            balance = sumIncome - sumExpense
            tv_home_expense.text = formatToRupiah(sumExpense.toLong())
            tv_home_balance.text = formatToRupiah(balance.toLong())
        })
        viewModel.getSumIncome().observe(viewLifecycleOwner, {
            if (it != null) {
                sumIncome = it
            }
            balance = sumIncome - sumExpense
            tv_home_income.text = formatToRupiah(sumIncome.toLong())
            tv_home_balance.text = formatToRupiah(balance.toLong())
        })

        viewModel.getListDate().observe(viewLifecycleOwner, Observer {
            homeMainAdapter.setData(it)
        })
        viewModel.getListNote().observe(viewLifecycleOwner, Observer {
            homeMainAdapter.setNote(it)
        })

       setFab()
    }

    private fun buildContainerTransformation() =
        MaterialContainerTransform().apply {
            scrimColor = Color.TRANSPARENT
            duration = 300
            interpolator = FastOutSlowInInterpolator()
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
        }

    private fun setFab() {
        fab_menu.post {
            val transition = MaterialFade().apply {
                duration = 1000
            }
            TransitionManager.beginDelayedTransition(requireActivity().findViewById(android.R.id.content), transition)
            fab_menu.visibility = View.VISIBLE
        }

        fab_menu.setOnClickListener {
            val transition = buildContainerTransformation()

            transition.startView = fab_menu
            transition.endView = card_menu

            transition.addTarget(card_menu)

            TransitionManager.beginDelayedTransition(requireActivity().findViewById(android.R.id.content), transition)
            card_menu.visibility = View.VISIBLE
            fab_scrim.visibility = View.VISIBLE

            fab_menu.visibility = View.INVISIBLE
        }

        fab_scrim.setOnClickListener {
            val transition = buildContainerTransformation()

            transition.startView = card_menu
            transition.endView = fab_menu

            transition.addTarget(fab_menu)

            TransitionManager.beginDelayedTransition(frame_layout, transition)
            card_menu.visibility = View.INVISIBLE
            fab_scrim.visibility = View.INVISIBLE

            fab_menu.visibility = View.VISIBLE
        }

        val listMenu = listOf(
            FabMenu(
                "Expense Limit",
                ExpenseLimitActivity::class.java
            ),
            FabMenu(
                "Input Income",
                InputIncomeActivity::class.java
            ),
            FabMenu(
                "Input Expense",
                InputExpenseActivity::class.java
            )
        )

        card_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = FabMenuAdapter(listMenu)
        }
    }
}