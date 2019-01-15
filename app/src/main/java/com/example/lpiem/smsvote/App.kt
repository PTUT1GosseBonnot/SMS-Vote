package com.example.lpiem.smsvote

import android.app.Application
import com.example.lpiem.smsvote.data.entity.ParseVote
import com.parse.Parse
import com.parse.ParseObject

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        ParseObject.registerSubclass(ParseVote::class.java)

        Parse.enableLocalDatastore(this)
        Parse.initialize(Parse.Configuration.Builder(this)
            .applicationId("1915484469352")
            .clientKey("195951")
            .server("https://bigoud.games/smsvote")
            .build()
        )
    }
}