package com.sssoyalan.soccerleague.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sssoyalan.soccerleague.R
import com.sssoyalan.soccerleague.models.MatchModel
import com.sssoyalan.soccerleague.models.Result
import kotlinx.android.synthetic.main.item_fixture.view.*

class FixtureAdapter: RecyclerView.Adapter<FixtureAdapter.FixtureViewHolder>() {
    inner class FixtureViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<MatchModel>() {
        override fun areItemsTheSame(oldItem: MatchModel, newItem: MatchModel): Boolean {
            return oldItem.matchWeek == newItem.matchWeek
        }
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MatchModel, newItem: MatchModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        return FixtureViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_fixture,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FixtureAdapter.FixtureViewHolder, position: Int) {
        var match_model : MatchModel = MatchModel()
        match_model = differ.currentList[position]

        holder.itemView.apply {
            if (position%2!=0){
                item_fixture_container.setBackgroundColor(resources.getColor(R.color.grey))
            }
            id_que.text=(position+1).toString()
            id_home.text=match_model.homeTeam
            away_team.text=match_model.awayTeam
        }
    }

    private var onItemClickListener : ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}

