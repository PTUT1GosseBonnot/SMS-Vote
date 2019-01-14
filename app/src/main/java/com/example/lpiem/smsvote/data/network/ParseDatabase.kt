package com.example.lpiem.smsvote.data.network

import com.example.lpiem.smsvote.data.entity.ParseVote
import com.example.lpiem.smsvote.presentation.ui.fragments.VoteListFragment
import com.parse.ParseQuery

class ParseDatabase {

    fun getAllVotesFromParse() {
        var query: ParseQuery<ParseVote> = ParseQuery.getQuery("Vote")
        query.fromLocalDatastore()
        query.findInBackground { voteList, e ->
            if (e == null) {
                VoteListFragment().showList(voteList)
            } else {
                VoteListFragment().showToastError()
            }
        }

    }

    fun addVoteToParse() {

    }
}