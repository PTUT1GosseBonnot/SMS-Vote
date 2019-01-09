package com.example.lpiem.smsvote.domain

import com.example.lpiem.smsvote.data.entity.Response
import com.example.lpiem.smsvote.data.entity.Vote

class VoteManager {

    val vote: Vote = Vote(null, ArrayList<Pair<Response, Int>>())



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

    fun addResponse(id: Int) {
        if (id >= 1 && id <= vote.responses.size)
            vote.responses[id-1] = vote.responses[id-1].copy(second = vote.responses[id-1].second.plus(1))
    }

    companion object {
        val instance = VoteManager()
    }

}