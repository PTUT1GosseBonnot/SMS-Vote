package com.example.lpiem.smsvote.domain

import com.example.lpiem.smsvote.data.entity.Response
import com.example.lpiem.smsvote.data.entity.Vote
import com.example.lpiem.smsvote.presentation.ui.adapter.AnswersSummaryAdapter

class VoteManager {

    val vote: Vote = Vote(null, ArrayList<Pair<Response, Int>>())
    val smsNumberAlreadyVote: ArrayList<String> = ArrayList()
    var numberOfSmsReceived: Int = 0
    var listening: Boolean = false
    var adapter: AnswersSummaryAdapter? = null


    fun setQuestion(question: String) {
        vote.question = question
    }

    fun clear() {
        vote.responses.clear()
    }

    fun addAnswers(answers: ArrayList<Response>) {
        for (answer in answers) {
            vote.responses.add(Pair(answer, 0))
        }
    }

    fun listen() {
        listening = true
    }

    fun stopListen() {
        listening = false
    }

    fun addResponse(id: Int) {
        if (id >= 1 && id <= vote.responses.size) {
            vote.responses[id - 1] = vote.responses[id - 1].copy(second = vote.responses[id - 1].second.plus(1))
            numberOfSmsReceived ++
            adapter?.notifyDataSetChanged()
        }
    }

    fun smsReceived(phoneNumber: String, message: String) {
        if (listening) {
            if (smsNumberAlreadyVote.contains(phoneNumber)) {
                return
            } else {
                smsNumberAlreadyVote.add(phoneNumber)
                addResponse(message.toInt())
            }
        }
    }

    fun attachAdapter(adapter: AnswersSummaryAdapter) {
        this.adapter = adapter
    }

    companion object {
        val instance = VoteManager()
    }

}