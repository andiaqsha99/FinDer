package com.tugas.www.finder.setting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tugas.www.finder.AlarmReceiver
import com.tugas.www.finder.SetPassCodeActivity
import com.tugas.www.finder.R
import com.tugas.www.finder.database.model.Plan
import com.tugas.www.finder.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_setting.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private val alarmReceiver = AlarmReceiver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val todayDate = dateFormat.format(calendar.time)

        val settingPreference = activity?.let { SettingPreference(it) }
        viewModel.setOngoingPlan(todayDate)

        if (settingPreference != null) {
            sw_expense_limit.isChecked = settingPreference.getExpenseLimitNotifSetting()
            sw_plan.isChecked = settingPreference.getPlanNotificationSetting()
            sw_expense_limit.setOnCheckedChangeListener { _, state ->
                settingPreference.setExpenseLimitNotifSetting(state)
            }

            sw_plan.setOnCheckedChangeListener { _, state ->
                settingPreference.setPlanNotificationSetting(state)
                viewModel.getOnGoingPlan().observe(viewLifecycleOwner, {
                    if (it != null) {
                        if (state) {
                            setPlanNotification(it)
                        } else {
                            cancelPlanNotification(it)
                        }
                    }
                })
            }
        }

        pass_code_card.setOnClickListener {
            startActivity(Intent(context, SetPassCodeActivity::class.java))
        }
    }

    private fun setPlanNotification(listPlan: List<Plan>) {
        for( plan in listPlan) {
            activity?.let { alarmReceiver.setAlarm(it, plan) }
        }
    }

    private fun cancelPlanNotification(listPlan: List<Plan>) {
        for( plan in listPlan) {
            activity?.let { alarmReceiver.cancelAlarm(it, plan.id_plan.toInt()) }
        }
    }
}