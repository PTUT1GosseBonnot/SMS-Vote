package com.example.lpiem.smsvote.domain

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.telephony.TelephonyManager
import com.example.lpiem.smsvote.data.entity.Response
import com.example.lpiem.smsvote.data.entity.Vote
import com.example.lpiem.smsvote.presentation.ui.adapter.AnswersSummaryAdapter
import com.example.lpiem.smsvote.presentation.ui.fragments.VoteSummaryFragment
import com.parse.ParseObject


class VoteManager {

    val vote: Vote = Vote(null, ArrayList<Pair<Response, Int>>())
    private val smsNumberAlreadyVote: ArrayList<String> = ArrayList()
    var numberOfSmsReceived: Int = 0
    var listening: Boolean = false
    var adapter: AnswersSummaryAdapter? = null
    var fragment: VoteSummaryFragment? = null


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
            numberOfSmsReceived++
            adapter?.notifyDataSetChanged()
            fragment?.notifySmsReceived()
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

    fun attachFragment(fragment: VoteSummaryFragment) {
        this.fragment = fragment
    }

    fun detachAdapter() {
        this.adapter = null
    }

    fun detachFragment() {
        this.fragment = null
    }

    fun uploadToParse(context: Context) {

        val parseVote = ParseObject.create("Vote")
        parseVote.put("vote", vote.question!!)
        parseVote.put("totalOfResponses", numberOfSmsReceived)
        parseVote.put("phoneNumber", getPhoneNumber(context))
        parseVote.saveInBackground {
            for (response in vote.responses) {
                val parseResponse = ParseObject.create("Response")
                parseResponse.put("response", response.first.response!!)
                parseResponse.put("numberOfResponses", response.second)
                parseResponse.saveInBackground {
                    val parseVoteResponse = ParseObject.create("VoteResponse")
                    parseVoteResponse.put("vote", parseVote.objectId)
                    parseVoteResponse.put("response", parseResponse.objectId)
                    parseVoteResponse.saveInBackground()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getPhoneNumber(context: Context): String {
        return (context.getSystemService(TELEPHONY_SERVICE) as TelephonyManager)
            .deviceId
    }

    companion object {
        val instance = VoteManager()
    }

}