package com.example.lpiem.smsvote.presentation.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.presentation.ui.fragments.VoteCreationFragment
import com.example.lpiem.smsvote.presentation.ui.fragments.VoteListFragment

class VoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, VoteListFragment())
                .commit()
        }
    }

}