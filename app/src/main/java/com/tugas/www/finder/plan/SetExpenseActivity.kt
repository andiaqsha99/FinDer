package com.tugas.www.finder.plan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import com.tugas.www.finder.MainActivity
import com.tugas.www.finder.R
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.database.model.Plan
import kotlinx.android.synthetic.main.activity_input_expense.*
import kotlinx.android.synthetic.main.activity_set_expense.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class SetExpenseActivity : AppCompatActivity() {

    private val viewModel by viewModel<PlanViewModel>()
    private lateinit var plan: Plan
    private lateinit var dateString: String
    private var amount: Int = 0
    private lateinit var notes: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_expense)

        et_expense_date_plan.inputType = InputType.TYPE_NULL
        et_expense_date_plan.setOnClickListener {
            val c = Calendar.getInstance()
            val years = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                c.set(year, monthOfYear, dayOfMonth)
                dateString = sdf.format(c.time)
                et_expense_date_plan.setText(dateString)
            }, years, month, day)
            dpd.show()
        }

        btn_expense_plan_cancel.setOnClickListener {
            onBackPressed()
        }

        btn_expense_plan_save.setOnClickListener {
            amount = et_expense_amount_plan.text.toString().toInt()
            notes = et_expense_note_plan.text.toString()
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val parser = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            dateString = sdf.format(parser.parse(dateString)!!)
            plan = Plan(
                text = notes,
                amount = amount,
                date = dateString,
                type = "expense"
            )
            viewModel.inputPlan(plan)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}