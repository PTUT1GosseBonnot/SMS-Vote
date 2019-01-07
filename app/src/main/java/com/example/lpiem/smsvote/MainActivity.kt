package com.example.lpiem.smsvote

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.lpiem.smsvote.utils.PermisionUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermisionUtil.askForPermission(this, Manifest.permission.RECEIVE_SMS, 15)
    }
}
