package com.example.lpiem.smsvote

import android.app.Application
import com.parse.Parse

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.enableLocalDatastore(this)
        Parse.initialize(Parse.Configuration.Builder(this)
            .applicationId("1915484469352")
            .server("https://bigoud.games/smsvote")
            .build()
        )
    }
}