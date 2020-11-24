package com.tugas.www.finder.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugas.www.finder.R
import com.tugas.www.finder.changeDateFormat
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.getWeekOfDate
import com.tugas.www.finder.home.HomeEntryAdapter
import kotlinx.android.synthetic.main.item_row_monetary_main.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryMainAdapter(private val type: String): RecyclerView.Adapter<HistoryMainAdapter.ViewHolder>() {

    private val listNote = ArrayList<Note>()
    private val listGroup = ArrayList<String>()
    private val viewPool = RecyclerView.RecycledViewPool()

    fun setListNote(data: List<Note>) {
        listNote.clear()
        listNote.addAll(data)
    }

    fun setListGroup(data: List<String>) {
        listGroup.clear()
        listGroup.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(group: String) {
            val homeEntryAdapter = HomeEntryAdapter()
            itemView.apply {
                when(type) {
                    "daily" -> {
                        tv_date.text = changeDateFormat("yyyy-MM-dd", "dd.MM.yyyy", group)
                    }
                    "weekly" -> {
                        val cal: Calendar = Calendar.getInstance()
                        cal.set(Calendar.WEEK_OF_YEAR, group.toInt())
                        val yourDate: Date = cal.time

                        cal.time = yourDate

                        val start: Date
                        val end: Date
                        val format1 = SimpleDateFormat("dd.MM.yyyy")

                        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
                        start = cal.time
                        cal.add(Calendar.DATE, 6)
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
                        end = cal.time

                        val weeklyDate = format1.format(start) + " - " + format1.format(end)
                        tv_date.text = weeklyDate
                    }
                    "monthly" -> {
                        tv_date.text = changeDateFormat("yyyy-MM", "MMMM yyyy", group)
                    }
                }

                rv_entry_monetary.let {
                    it.layoutManager = LinearLayoutManager(rv_entry_monetary.context)
                    it.adapter = homeEntryAdapter
                    it.setRecycledViewPool(viewPool)
                }
            }
            when(type) {
                "daily" -> {
                    homeEntryAdapter.setData(listNote.filter { note -> note.date.equals(group) })
                }
                "weekly" -> {
                    homeEntryAdapter.setData(listNote.filter { note -> group == getWeekOfDate(note.date!!) })
                }
                "monthly" -> {
                    homeEntryAdapter.setData(listNote.filter { note -> note.date!!.contains(group) })
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_monetary_main,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listGroup.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGroup[position])
    }
}