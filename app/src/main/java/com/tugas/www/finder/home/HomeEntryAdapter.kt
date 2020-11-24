package com.tugas.www.finder.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tugas.www.finder.R
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.formatToRupiah
import kotlinx.android.synthetic.main.item_row_monetary_entry.view.*

class HomeEntryAdapter: RecyclerView.Adapter<HomeEntryAdapter.HomeEntryViewHolder>() {

    private val listEntry = ArrayList<Note>()

    fun setData(data: List<Note>) {
        listEntry.clear()
        listEntry.addAll(data)
        notifyDataSetChanged()
    }

    inner class HomeEntryViewHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bind(note: Note) {
            itemView.apply {
                tv_expense_note.text = "-"
                tv_expense_amount.text = "-"
                tv_income_note.text = "-"
                tv_income_amount.text = "-"
            }
            if (note.type == "income") {
                itemView.apply {
                    tv_income_amount.text = formatToRupiah(note.amount.toLong())
                    tv_income_note.text = note.text
                }
            } else {
                itemView.apply {
                    tv_expense_amount.text = formatToRupiah(note.amount.toLong())
                    tv_expense_note.text = note.text
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeEntryViewHolder {
        return HomeEntryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_monetary_entry,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listEntry.size

    override fun onBindViewHolder(holder: HomeEntryViewHolder, position: Int) {
        holder.bind(listEntry[position])
    }
}