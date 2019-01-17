package com.example.lpiem.smsvote.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.example.lpiem.smsvote.R


class PermisionUtil {

    companion object {

        fun askForSMSPermission(context: Activity, permission: String, requestCode: Int?) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {

                    val builder = AlertDialog.Builder(context)
                    builder.setTitle(R.string.missingPermissions)
                        .setMessage(R.string.allowAddPermissionsSMS)
                        .setPositiveButton(
                            R.string.allow
                        ) { _, _ ->
                            ActivityCompat.requestPermissions(
                                context,
                                arrayOf(permission),
                                requestCode!!
                            )
                        }
                        .setCancelable(false)
                    builder.show()


                } else {

                    ActivityCompat.requestPermissions(context, arrayOf(permission), requestCode!!)
                }
            }
        }

        fun askForPhoneStatePermission(context: Activity, permission: String, requestCode: Int?) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {

                    val builder = AlertDialog.Builder(context)
                    builder.setTitle(R.string.missingPermissions)
                        .setMessage(R.string.allowedPermissionsPhoneState)
                        .setPositiveButton(
                            R.string.allow
                        ) { _, _ ->
                            ActivityCompat.requestPermissions(
                                context,
                                arrayOf(permission),
                                requestCode!!
                            )
                        }
                        .setCancelable(false)
                    builder.show()
                } else {
                    ActivityCompat.requestPermissions(context, arrayOf(permission), requestCode!!)
                }
            }
        }

        fun onRequestPermissionsResult(context: Activity, requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            when (requestCode) {
                15 -> askForSMSPermission(context, permissions[0], requestCode)
                30 -> askForPhoneStatePermission(context, permissions[0], requestCode)
            }
        }

    }

}