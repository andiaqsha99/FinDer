package com.tugas.www.finder.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugas.www.finder.R
import com.tugas.www.finder.database.model.Note
import com.tugas.www.finder.home.HomeEntryAdapter
import kotlinx.android.synthetic.main.item_row_monetary_main.view.*

class HistoryDailyAdapter(private val type: String): RecyclerView.Adapter<HistoryDailyAdapter.ViewHolder>() {

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
                tv_date.text = group
                rv_entry_monetary.let {
                    it.layoutManager = LinearLayoutManager(rv_entry_monetary.context)
                    it.adapter = homeEntryAdapter
                    it.setRecycledViewPool(viewPool)
                }
            }
            when(type) {
                "daily" -> {
                    homeEntryAdapter.setData( listNote.filter { note -> note.date.equals(group) })
                }
                "weekly" -> {
                    homeEntryAdapter.setData( listNote.filter { note -> note.date!!.contains(group) })
                }
                "monthly" -> {
                    homeEntryAdapter.setData( listNote.filter { note -> note.date!!.contains(group) })
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