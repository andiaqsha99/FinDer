package com.tugas.www.finder.calendar

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialFade
import com.tugas.www.finder.R
import com.tugas.www.finder.expenselimit.ExpenseLimitActivity
import com.tugas.www.finder.fab.FabMenu
import com.tugas.www.finder.fab.FabMenuAdapter
import com.tugas.www.finder.inputmonetary.InputExpenseActivity
import com.tugas.www.finder.inputmonetary.InputIncomeActivity
import com.tugas.www.finder.plan.SetExpenseActivity
import com.tugas.www.finder.plan.SetIncomeActivity
import kotlinx.android.synthetic.main.fragment_calendar.*

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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