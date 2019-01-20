package com.example.lpiem.smsvote.presentation.presenter

import com.example.lpiem.smsvote.base.BaseView
import com.example.lpiem.smsvote.data.entity.ParseVote

interface VoteListView : BaseView {
    fun showList(list : List<ParseVote>)
}