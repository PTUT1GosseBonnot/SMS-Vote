package com.example.lpiem.smsvote.utils

import android.app.Activity
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.example.lpiem.smsvote.R


class PermisionUtil {

    companion object {
        fun askForPermission(context: Activity, permission: String, requestCode: Int?) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {

                    val builder = AlertDialog.Builder(context)
                    builder.setTitle(R.string.missingPermissions)
                        .setMessage(R.string.allowAddPermissionsSMS)
                        .setPositiveButton(R.string.allow
                        ) { _, _ ->
                            ActivityCompat.requestPermissions(
                                context,
                                arrayOf(permission),
                                requestCode!!
                            )
                        }
                    builder.show()


                } else {

                    ActivityCompat.requestPermissions(context, arrayOf(permission), requestCode!!)
                }
            }
        }
    }

}