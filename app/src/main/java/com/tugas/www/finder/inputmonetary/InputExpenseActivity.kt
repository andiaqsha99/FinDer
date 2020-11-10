package com.tugas.www.finder.inputmonetary

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import com.tugas.www.finder.MainActivity
import com.tugas.www.finder.R
import com.tugas.www.finder.database.model.Note
import kotlinx.android.synthetic.main.activity_input_expense.*
import kotlinx.android.synthetic.main.activity_input_income.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class InputExpenseActivity : AppCompatActivity() {

    private val viewModel by viewModel<InputNoteViewModel>()
    private lateinit var note: Note
    private lateinit var dateString: String
    private var amount: Int = 0
    private lateinit var notes: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_expense)

        et_expense_date.inputType = InputType.TYPE_NULL
        et_expense_date.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                c.set(year, monthOfYear, dayOfMonth)
                dateString = sdf.format(c.time)
                et_expense_date.setText(dateString)
            }, year, month, day)
            dpd.show()
        }

        btn_expense_cancel.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btn_expense_save.setOnClickListener {
            amount = et_expense_amount.text.toString().toInt()
            notes = et_expense_note.text.toString()
            note = Note(
                text = notes,
                amount = amount,
                date = dateString,
                type = "expense"
            )
            viewModel.inputNote(note)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}