package com.tugas.www.finder.inputmonetary

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import com.tugas.www.finder.MainActivity
import com.tugas.www.finder.R
import com.tugas.www.finder.database.model.Note
import kotlinx.android.synthetic.main.activity_input_income.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class InputIncomeActivity : AppCompatActivity() {

    private val viewModel by viewModel<InputNoteViewModel>()
    private lateinit var note: Note
    private lateinit var dateString: String
    private var amount: Int = 0
    private lateinit var notes: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_income)

        et_income_date.inputType = InputType.TYPE_NULL
        et_income_date.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                c.set(year, monthOfYear, dayOfMonth)
                dateString = sdf.format(c.time)
                et_income_date.setText(dateString)
            }, year, month, day)
            dpd.show()
        }

        btn_income_cancel.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btn_income_save.setOnClickListener {
            amount = et_income_amount.text.toString().toInt()
            notes = et_income_note.text.toString()
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val parser = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            dateString = sdf.format(parser.parse(dateString)!!)
            note = Note(
                text = notes,
                amount = amount,
                date = dateString,
                type = "income"
            )
            viewModel.inputNote(note)
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}