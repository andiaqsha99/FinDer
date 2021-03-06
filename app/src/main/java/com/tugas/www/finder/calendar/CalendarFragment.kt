package com.tugas.www.finder.calendar

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialFade
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.tugas.www.finder.R
import com.tugas.www.finder.changeDateFormat
import com.tugas.www.finder.expenselimit.ExpenseLimitActivity
import com.tugas.www.finder.fab.FabMenu
import com.tugas.www.finder.fab.FabMenuAdapter
import com.tugas.www.finder.formatToRupiah
import com.tugas.www.finder.plan.SetExpenseActivity
import com.tugas.www.finder.plan.SetIncomeActivity
import kotlinx.android.synthetic.main.dialog_calendar.view.*
import kotlinx.android.synthetic.main.fragment_calendar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : Fragment() {

    private val viewModel by viewModel<CalendarViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFab()

        viewModel.setListDate()
        val dateEvent : ArrayList<CalendarDay> = arrayListOf()
        viewModel.getListDate().observe(viewLifecycleOwner, Observer {list ->
            if (list != null) {
                for (date in list) {
                    val array = date.split("-").map { it.toInt() }
                    dateEvent.add(CalendarDay.from(array[0], array[1], array[2]))
                }
            }
            calendarView.addDecorators(EventDayDecorator(dateEvent))
        })

        calendarView.setOnDateChangedListener { widget, dates, selected ->
            if (dates.day in 1..9) {
                viewModel.setPlanAndNote(dates.year.toString()+"-"+dates.month.toString()+"-0"+dates.day.toString())
            } else {
                viewModel.setPlanAndNote(dates.year.toString() + "-" + dates.month.toString() + "-" + dates.day.toString())
            }

            val newDate = changeDateFormat(
                "dd MM yyyy",
            "dd MMMM yyyy",
                dates.day.toString() +" "+ dates.month.toString() +" "+ dates.year.toString()
            )

            val builder = activity?.let { AlertDialog.Builder(it) }
            val layout = layoutInflater.inflate(R.layout.dialog_calendar, null)
            builder?.apply {
                setView(layout)
                setCancelable(true)
            }

            layout.apply {
                tv_dialog_date.text = newDate
            }

            viewModel.apply {
                getExpenseNote().observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        layout.tv_dialog_expense_note.text = it.text
                        layout.tv_dialog_expense_amount.text = formatToRupiah(it.amount.toLong())
                    } else {
                        layout.tv_dialog_expense_amount.text = formatToRupiah(0)
                        layout.tv_dialog_expense_note.text = "-"
                    }
                })
                getIncomeNote().observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        layout.tv_dialog_income_note.text = it.text
                        layout.tv_dialog_income_amount.text = formatToRupiah(it.amount.toLong())
                    } else {
                        layout.tv_dialog_income_amount.text = formatToRupiah(0)
                        layout.tv_dialog_income_note.text = "-"
                    }
                })
                getExpensePlan().observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        layout.tv_dialog_expense_plan_note.text = it.text
                        layout.tv_dialog_expense_plan_amount.text = formatToRupiah(it.amount.toLong())
                    } else {
                        layout.tv_dialog_expense_plan_amount.text = formatToRupiah(0)
                        layout.tv_dialog_expense_plan_note.text = "-"
                    }
                })
                getIncomePlan().observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        layout.tv_dialog_income_plan_note.text = it.text
                        layout.tv_dialog_income_plan_amount.text = formatToRupiah(it.amount.toLong())
                    } else {
                        layout.tv_dialog_income_plan_amount.text = formatToRupiah(0)
                        layout.tv_dialog_income_plan_note.text = "-"
                    }
                })
            }

            val dialog = builder?.create()
            dialog?.show()
        }
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
                "Add/Edit Expense Plan",
                SetExpenseActivity::class.java
            ),
            FabMenu(
                "Add/Edit Income Plan",
                SetIncomeActivity::class.java
            )
        )

        card_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@CalendarFragment.context)
            adapter = FabMenuAdapter(listMenu)
        }
    }
}