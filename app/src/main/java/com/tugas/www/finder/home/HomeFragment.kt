package com.tugas.www.finder.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialFade
import com.tugas.www.finder.R
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.database.model.User
import com.tugas.www.finder.database.repository.NoteRepository
import com.tugas.www.finder.expenselimit.ExpenseLimitActivity
import com.tugas.www.finder.expenselimit.ExpenseLimitViewModel
import com.tugas.www.finder.fab.FabMenu
import com.tugas.www.finder.fab.FabMenuAdapter
import com.tugas.www.finder.inputmonetary.InputExpenseActivity
import com.tugas.www.finder.inputmonetary.InputIncomeActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
import kotlin.reflect.typeOf

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private val viewBudget by viewModel<ExpenseLimitViewModel>()
    private lateinit var user: User
    private lateinit var homeMainAdapter: HomeMainAdapter

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
        }
        rv_main_monetary.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeMainAdapter
        }
        viewModel.getListDate().observe(viewLifecycleOwner, Observer {
            homeMainAdapter.setData(it)
        })
        viewModel.getListNote().observe(viewLifecycleOwner, Observer {
            homeMainAdapter.setNote(it)
            var income: Int = 0
            var expense: Int = 0

            for (i in it.indices){
                if(it[i].type == "income"){
                    income += it[i].amount
                }else{
                    expense += it[i].amount
                }
            }

            total_income.text = "Rp$income"
            total_expense.text = "Rp$expense"
        })

        viewBudget.getUserData().observe(viewLifecycleOwner, Observer {
            user = it
            try {
                if (user.monthly_limit == 0) {
                    budget.text = "No Monthly Budget"
                } else {
                    budget.text = "Rp${user.monthly_limit}"
                }
            } catch (e: Exception) {
                budget.text = "No Monthly Budget"
            }
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