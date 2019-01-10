package com.example.lpiem.smsvote.data.entity

data class Vote(var question: String?, val responses: ArrayList<Pair<Response, Int>>)