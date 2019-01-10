package com.example.lpiem.smsvote.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.data.entity.Response
import com.example.lpiem.smsvote.domain.VoteManager
import kotlinx.android.synthetic.main.recycler_view_answer_summary_item.view.*

class AnswersSummaryAdapter(private val answersList: ArrayList<Pair<Int, Pair<Response, Int>>>) : RecyclerView.Adapter<AnswersSummaryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AnswersSummaryAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_answer_summary_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return answersList.size
    }

    override fun onBindViewHolder(holder: AnswersSummaryAdapter.ViewHolder, p1: Int) {
        return holder.bindItems()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context = itemView.context
        val textView = itemView.textViewAnswer
        val voteManager = VoteManager.instance

        fun bindItems() {
            itemView.textViewId.text = voteManager.vote.responses[adapterPosition].first.id.toString()
            itemView.textViewAnswer.text = voteManager.vote.responses[adapterPosition].first.response
            itemView.textViewResponseNumber.text = voteManager.vote.responses[adapterPosition].second.toString()
        }


    }

}