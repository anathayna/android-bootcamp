package com.anathayna.blackboard

import android.content.Context
import android.app.AlertDialog

fun alert(title: String, msg: String, context: Context) {
    val builder = AlertDialog.Builder(context)

    builder
        .setTitle(title)
        .setMessage(msg)
        .setPositiveButton("OK", null)
        .create()
        .show()
}