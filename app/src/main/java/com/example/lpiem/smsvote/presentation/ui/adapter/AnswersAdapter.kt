package com.example.lpiem.smsvote.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.data.entity.Response
import kotlinx.android.synthetic.main.recycler_view_answer_item.view.*


class AnswersAdapter(private val answersList: ArrayList<Response>) : RecyclerView.Adapter<AnswersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_answer_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems()
    }

    override fun getItemCount(): Int {
        return answersList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context = itemView.context
        val editText = itemView.editTextAnswer

        init {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                    answersList[adapterPosition].response = editText.text.toString()
                }

                override fun afterTextChanged(editable: Editable) {

                }
            })
        }

        fun bindItems() {
            itemView.editTextAnswer.setText(answersList[adapterPosition].response)
            itemView.textViewId.text = answersList[adapterPosition].id.toString()
        }
    }
}