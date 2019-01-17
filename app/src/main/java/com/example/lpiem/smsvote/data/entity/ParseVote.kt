package com.example.lpiem.smsvote.data.entity

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseQuery

@ParseClassName("Vote")
class ParseVote: ParseObject() {

    fun getVote(): String? {
        return getString("vote")
    }

    fun setVote(value: String) {
        put("vote", value)
    }

    companion object {
        fun getQuery(): ParseQuery<ParseVote> {
            return ParseQuery.getQuery(ParseVote::class.java)
        }
    }

}