package com.example.lpiem.smsvote.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.entity.Response
import kotlinx.android.synthetic.main.recycler_view_answer_summary_item.view.*

class AnswerSummaryAdapter(private val answersList: ArrayList<Response>) : RecyclerView.Adapter<AnswerSummaryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AnswerSummaryAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_answer_summary_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return answersList.size
    }

    override fun onBindViewHolder(holder: AnswerSummaryAdapter.ViewHolder, p1: Int) {
        return holder.bindItems()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context = itemView.context
        val textView = itemView.textViewAnswer

        fun bindItems() {
            itemView.textViewAnswer.setText(answersList[adapterPosition].response)
            itemView.textViewId.text = "Test"
        }


    }

}