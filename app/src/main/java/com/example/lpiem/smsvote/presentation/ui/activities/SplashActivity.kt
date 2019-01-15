package com.example.lpiem.smsvote.presentation.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.lpiem.smsvote.R
import com.example.lpiem.smsvote.utils.PermisionUtil
import java.security.Permission

class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000 //2 seconds

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            PermisionUtil.askForSMSPermission(this, Manifest.permission.RECEIVE_SMS, 15)
            PermisionUtil.askForPhoneStatePermission(this, Manifest.permission.READ_PHONE_STATE, 30)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {

            } else {
                PermisionUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
            }
        }
        if (checkAllPermissionsGranted()) {
            val intent = Intent(applicationContext, VoteCreationActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun checkAllPermissionsGranted(): Boolean {
        return (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
    }
}
