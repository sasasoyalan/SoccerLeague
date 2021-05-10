package com.sssoyalan.soccerleague.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sssoyalan.soccerleague.R
import com.sssoyalan.soccerleague.models.Result
import kotlinx.android.synthetic.main.item_table.view.*

class TableAdapter: RecyclerView.Adapter<TableAdapter.TableViewHolder>() {
    inner class TableViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.teamId == newItem.teamId
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        return TableViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_table,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TableAdapter.TableViewHolder, position: Int) {
        val team = differ.currentList[position]

        holder.itemView.apply {
            if (position%2!=0){
                item_table_container.setBackgroundColor(resources.getColor(R.color.grey))
            }
            id_que.text=team.teamId
            id_team.text=team.Team
            id_mp.text="0"
            id_w.text="0"
            id_l.text="0"
            id_pts.text="0"
            setOnClickListener{
                onItemClickListener?.let {  it(team) }
            }
        }
    }

    private var onItemClickListener : ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}