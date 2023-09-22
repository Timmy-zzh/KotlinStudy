package com.timmy.trending.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timmy.trending.R
import com.timmy.trending.data.entity.RepoList

class RepoAdapter(private val repoList: RepoList) : RecyclerView.Adapter<RepoAdapter.RepoHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        return RepoHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        )
    }

    override fun getItemCount(): Int = repoList.count

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        holder.text.text = repoList.items.getOrNull(position)?.repo
    }

    class RepoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text)
    }
}
