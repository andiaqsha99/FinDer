package com.tugas.www.finder.expenselimit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.tugas.www.finder.R
import com.tugas.www.finder.database.model.User
import kotlinx.android.synthetic.main.fragment_weekly_expense_limit.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [WeeklyExpenseLimitFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeeklyExpenseLimitFragment : Fragment() {

    private val viewModel by sharedViewModel<ExpenseLimitViewModel>()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weekly_expense_limit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserData().observe(viewLifecycleOwner, Observer {
            user = it

            et_weekly_limit.setText(user.weekly_limit.toString())

            btn_set_weekly.setOnClickListener {
                user.weekly_limit = et_weekly_limit.text.toString().toInt()
                viewModel.updateUser(user)
            }

            btn_delete_weekly.setOnClickListener {
                user.weekly_limit = 0
                viewModel.updateUser(user)
                et_weekly_limit.setText("0")
            }
        })
    }
}