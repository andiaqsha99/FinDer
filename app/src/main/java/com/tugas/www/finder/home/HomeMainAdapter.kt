package com.tugas.www.finder.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugas.www.finder.R
import com.tugas.www.finder.changeDateFormat
import com.tugas.www.finder.database.model.Note
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_row_monetary_main.view.*

class HomeMainAdapter: RecyclerView.Adapter<HomeMainAdapter.HomeMainAdapterViewHolder>() {

    private val listDate = ArrayList<String>()
    private val listNote = ArrayList<Note>()
    private val viewPool = RecyclerView.RecycledViewPool()

    fun setData(data: List<String>) {
        listDate.clear()
        listDate.addAll(data)
        notifyDataSetChanged()
    }

    fun setNote(data: List<Note>) {
        listNote.clear()
        listNote.addAll(data)
    }

    inner class HomeMainAdapterViewHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bind(date: String) {
            val homeEntryAdapter = HomeEntryAdapter()

            itemView.apply {
                tv_date.text = changeDateFormat("yyyy-MM-dd", "dd.MM.yyyy", date)
                rv_entry_monetary.let {
                    it.layoutManager = LinearLayoutManager(rv_entry_monetary.context)
                    it.adapter = homeEntryAdapter
                    it.setRecycledViewPool(viewPool)
                }
            }
            homeEntryAdapter.setData(listNote.filter { note -> note.date.equals(date) })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMainAdapterViewHolder {
        return HomeMainAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_monetary_main,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listDate.size

    override fun onBindViewHolder(holder: HomeMainAdapterViewHolder, position: Int) {
        holder.bind(listDate[position])
    }
}