package com.tugas.www.finder.fab

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.tugas.www.finder.inputmonetary.InputExpenseActivity
import com.tugas.www.finder.inputmonetary.InputIncomeActivity
import com.tugas.www.finder.R
import kotlinx.android.synthetic.main.item_fab_menu.view.*

class FabMenuAdapter(private val listMenu: List<FabMenu>): RecyclerView.Adapter<FabMenuAdapter.HomeFabViewHolder>() {

    inner class HomeFabViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(fabMenu: FabMenu) {
            itemView.menu_title.apply {
                text = fabMenu.title
                setOnClickListener {
                    startActivity(itemView.context, Intent(itemView.context, fabMenu.activity), null)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFabViewHolder {
        return HomeFabViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_fab_menu, parent, false)
        )
    }

    override fun getItemCount(): Int = listMenu.size

    override fun onBindViewHolder(holder: HomeFabViewHolder, position: Int) {
        holder.bind(listMenu[position])
    }
}